package app.suhocki.diframeworksoverview

import android.content.ContentResolver
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
import app.suhocki.diframeworksoverview.presentation.settings.SettingsFragment
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.factory
import org.koin.dsl.module

fun mainModule() = module {
    scope<MainActivity> {
        scoped<ContentResolver> { get<Context>().contentResolver }

        factory<AuthorizationApi>()
        factory<LoginRepository>()
        factory<ErrorHandler>()
        factory<SecurityManager>()
        factory<AccountFragment>()
        factory<SettingsFragment>()

        factory<DeviceController>()

        factory<SharedPreferencesProvider>()
        factory { get<SharedPreferencesProvider>().get() }
        factory<SharedPreferencesWrapper>() bind Preferences::class

        factory<EncryptedSharedPreferencesProvider>()
        factory(named("encrypted")) { get<EncryptedSharedPreferencesProvider>().get() }
        factory { UserManager(get(named("encrypted"))) }
    }
}