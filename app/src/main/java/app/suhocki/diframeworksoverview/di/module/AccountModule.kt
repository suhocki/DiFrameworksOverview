package app.suhocki.diframeworksoverview.di.module

import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Module
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment

class AccountModule(
    private val userPreferences: UserPreferences,
    private val userManager: UserManager
) : Module {
    val accountFragment: AccountFragment
        get() = AccountFragment(userPreferences, userManager)
}