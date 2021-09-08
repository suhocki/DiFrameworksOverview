package app.suhocki.diframeworksoverview.di.scope

import android.content.Context
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Scope
import app.suhocki.diframeworksoverview.di.ScopeHolder
import app.suhocki.diframeworksoverview.di.module.UserModule
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScopeProvider

class UserScope(
    private val userManager: UserManager,
    context: Context,
    userName: String,
) : Scope {
    override val module = UserModule(context, userName)

    val scopes = Scopes()

    override fun clear() {
        super.clear()
        userManager.clear()
        scopes.account.clear()
        scopes.settings.clear()
    }

    inner class Scopes {
        val account = AccountScopeHolder()
        val settings = SettingsScopeHolder()

        inner class AccountScopeHolder : ScopeHolder<AccountScope>() {
            override fun create() {
                scope = AccountScope(module.userPreferences, userManager)
            }
        }

        inner class SettingsScopeHolder : ScopeHolder<SettingsScope>() {
            override fun create() {
                scope = SettingsScopeProvider.get().getSettingsScope(module.userPreferences)
            }
        }
    }
}