package app.suhocki.diframeworksoverview.di

import android.content.Context
import android.content.SharedPreferences
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScopeProvider

class UserScope(
    private val userManager: UserManager,
    private val context: Context,
    private val userName: String,
) {
    val module = Module()
    val scopes = Scopes()

    fun clear() {
        userManager.clear()
        scopes.account.clear()
        scopes.settings.clear()
    }

    inner class Module {
        private val sharedPreferences: SharedPreferences
            get() = context.getSharedPreferences(userName, Context.MODE_PRIVATE)

        val userPreferences: UserPreferences
            get() = UserPreferences(sharedPreferences)
    }

    inner class Scopes {
        val account = Account()
        val settings = Settings()

        inner class Account {
            private var accountScope: AccountScope? = null

            fun create() {
                accountScope = AccountScope(module.userPreferences, userManager)
            }

            fun get() = requireNotNull(accountScope)

            fun clear() {
                accountScope = null
            }
        }

        inner class Settings {
            private var settingsScope: SettingsScope? = null

            fun create() {
                settingsScope = SettingsScopeProvider.get().getSettingsScope(module.userPreferences)
            }

            fun get() = requireNotNull(settingsScope)

            fun clear() {
                settingsScope = null
            }
        }
    }
}