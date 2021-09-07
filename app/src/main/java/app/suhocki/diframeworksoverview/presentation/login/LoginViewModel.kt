package app.suhocki.diframeworksoverview.presentation.login

import app.suhocki.diframeworksoverview.data.authorization.LoginRepository
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val errorHandler: ErrorHandler,
    private val userManager: UserManager,
    private val loginRepository: LoginRepository,
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val _screenState = MutableStateFlow(LoginFragment.ScreenState())

    val screenState: Flow<LoginFragment.ScreenState> = _screenState

    init {
        userManager.currentUser?.let { userName ->
            _screenState.emit { copy(userName = userName) }
        }
    }

    fun login(user: String) = coroutineScope.launch {
        _screenState.emit { copy(isProgress = true) }
        runCatching {
            loginRepository.login(user)
        }.onSuccess { securityToken ->
            userManager.currentUser = user
            userManager.securityToken = securityToken
            _screenState.emit { copy(userName = user) }
        }.onFailure { error ->
            errorHandler.onError(error)
            _screenState.emit { copy(isProgress = false) }
        }
    }

    fun clear() {
        coroutineScope.cancel()
    }

    private inline fun <T> MutableStateFlow<T>.emit(block: T.() -> T) {
        value = value.block()
    }
}