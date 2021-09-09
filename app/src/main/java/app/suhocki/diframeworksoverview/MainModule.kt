package app.suhocki.diframeworksoverview

import android.content.Context
import app.suhocki.diframeworksoverview.data.authorization.AuthorizationApi
import app.suhocki.diframeworksoverview.data.authorization.LoginRepository
import app.suhocki.diframeworksoverview.data.device.DeviceController
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesProvider
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesWrapper
import app.suhocki.diframeworksoverview.data.security.SecurityManager
import app.suhocki.diframeworksoverview.data.user.EncryptedSharedPreferencesProvider
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

fun mainModule(context: Context) = DI.Module(name = "main") {
    bind { instance(context) }
    bind { instance(context.contentResolver) }

    bind { provider { SharedPreferencesProvider(instance(), instance()) } }

    bind { provider { instance<SharedPreferencesProvider>().get() } }

    bind { provider { EncryptedSharedPreferencesProvider(instance()) } }
    bind(tag = "encrypted") { provider { instance<EncryptedSharedPreferencesProvider>().get() } }

    bind { provider { AuthorizationApi() } }
    bind { provider { LoginRepository(instance(), instance()) } }
    bind { provider { DeviceController(instance()) } }
    bind { provider { ErrorHandler() } }
    bind<Preferences> { provider { SharedPreferencesWrapper(instance()) } }
    bind { provider { SecurityManager(instance()) } }
    bind { provider { UserManager(instance(tag = "encrypted")) } }

    bind(AccountFragment::class.qualifiedName) { provider { AccountFragment(instance(), instance()) } }
    bind(LoginFragment::class.qualifiedName) { provider { LoginFragment(instance()) } }
}
