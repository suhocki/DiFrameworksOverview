package app.suhocki.diframeworksoverview.presentation.login

import app.suhocki.diframeworksoverview.data.authorization.LoginRepository
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.login.LoginScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@LoginScope
class LoginViewModel @Inject constructor(
    val errorHandler: ErrorHandler,
    private val userManager: UserManager,
    private val loginRepository: LoginRepository,
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val _screenState = MutableStateFlow(LoginFragment.ScreenState())

    val screenState: Flow<LoginFragment.ScreenState> = _screenState

    init {
        if (userManager.currentUser != null) {
            _screenState.emit { copy(isLoginCompleted = true) }
        }
    }

    fun login(user: String) = coroutineScope.launch {
        _screenState.emit { copy(isProgress = true) }
        runCatching {
            loginRepository.login(user)
        }.onSuccess { securityToken ->
            userManager.currentUser = user
            userManager.securityToken = securityToken
            _screenState.emit { copy(isLoginCompleted = true) }
        }.onFailure { error ->
            errorHandler.onError(error)
            _screenState.emit { copy(isProgress = false) }
        }
    }

    private inline fun <T> MutableStateFlow<T>.emit(block: T.() -> T) {
        value = value.block()
    }
}