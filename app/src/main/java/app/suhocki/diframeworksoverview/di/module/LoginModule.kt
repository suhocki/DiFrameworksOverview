package app.suhocki.diframeworksoverview.di.module

import android.content.ContentResolver
import android.content.Context
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.data.authorization.AuthorizationApi
import app.suhocki.diframeworksoverview.data.authorization.LoginRepository
import app.suhocki.diframeworksoverview.data.device.DeviceController
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.security.SecurityManager
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Module
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginViewModel

class LoginModule(
    private val context: Context,
    errorHandler: ErrorHandler,
    userManager: UserManager,
) : Module {
    private val contentResolver: ContentResolver
        get() = context.contentResolver

    private val deviceController: DeviceController
        get() = DeviceController(contentResolver)

    private val securityManager: SecurityManager
        get() = SecurityManager(deviceController)

    private val authorizationApi: AuthorizationApi
        get() = AuthorizationApi()

    private val loginRepository: LoginRepository
        get() = LoginRepository(authorizationApi, securityManager)

    private val loginViewModel = LoginViewModel(errorHandler, userManager, loginRepository)

    val loginFragment: Fragment
        get() = LoginFragment(loginViewModel)

    override fun clear() {
        loginViewModel.clear()
    }
}