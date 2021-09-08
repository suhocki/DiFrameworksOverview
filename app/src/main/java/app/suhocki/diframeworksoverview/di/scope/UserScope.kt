package app.suhocki.diframeworksoverview.di.scope

import android.content.Context
import android.content.SharedPreferences
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Module
import app.suhocki.diframeworksoverview.di.Scope
import app.suhocki.diframeworksoverview.di.ScopeHolder
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScopeProvider

class UserScope(
    private val userManager: UserManager,
    private val context: Context,
    private val userName: String,
) : Scope {
    override val module = UserModule()
    val scopes = Scopes()

    override fun clear() {
        super.clear()
        userManager.clear()
        scopes.account.clear()
        scopes.settings.clear()
    }

    inner class UserModule : Module {
        private val sharedPreferences: SharedPreferences
            get() = context.getSharedPreferences(userName, Context.MODE_PRIVATE)

        val userPreferences: UserPreferences
            get() = UserPreferences(sharedPreferences)
    }

    inner class Scopes {
        val account = Account()
        val settings = Settings()

        inner class Account : ScopeHolder<AccountScope>() {
            override fun create() {
                scope = AccountScope(module.userPreferences, userManager)
            }
        }

        inner class Settings : ScopeHolder<SettingsScope>() {
            override fun create() {
                scope = SettingsScopeProvider.get().getSettingsScope(module.userPreferences)
            }
        }
    }
}