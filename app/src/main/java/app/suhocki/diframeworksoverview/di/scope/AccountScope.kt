package app.suhocki.diframeworksoverview.di.scope

import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Module
import app.suhocki.diframeworksoverview.di.Scope
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment

class AccountScope(
    private val userPreferences: UserPreferences,
    private val userManager: UserManager
) : Scope {
    override val module = AccountModule()

    inner class AccountModule : Module {
        val accountFragment: AccountFragment
            get() = AccountFragment(userPreferences, userManager)
    }
}