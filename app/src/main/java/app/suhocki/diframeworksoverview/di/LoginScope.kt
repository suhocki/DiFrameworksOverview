package app.suhocki.diframeworksoverview.di

import android.content.ContentResolver
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.data.authorization.AuthorizationApi
import app.suhocki.diframeworksoverview.data.authorization.LoginRepository
import app.suhocki.diframeworksoverview.data.device.DeviceController
import app.suhocki.diframeworksoverview.data.security.SecurityManager
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginViewModel

class LoginScope(private val appScope: AppScope) {

    private val contentResolver: ContentResolver
        get() = appScope.context.contentResolver

    private val deviceController: DeviceController
        get() = DeviceController(contentResolver)

    private val securityManager: SecurityManager
        get() = SecurityManager(deviceController)

    private val authorizationApi: AuthorizationApi
        get() = AuthorizationApi()

    private val loginRepository: LoginRepository
        get() = LoginRepository(
            authorizationApi = authorizationApi,
            securityManager = securityManager
        )

    private val loginViewModel = LoginViewModel(
        errorHandler = appScope.errorHandler,
        userManager = appScope.userManager,
        loginRepository = loginRepository
    )

    val loginFragment: Fragment
        get() = LoginFragment(loginViewModel)

    fun clear() {
        loginViewModel.clear()
    }
}