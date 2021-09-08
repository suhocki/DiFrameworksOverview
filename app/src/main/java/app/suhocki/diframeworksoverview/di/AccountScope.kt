package app.suhocki.diframeworksoverview.di

import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment

class AccountScope(
    private val userPreferences: UserPreferences,
    private val userManager: UserManager
) {
    val module = Module()

    inner class Module {
        val accountFragment: AccountFragment
            get() = AccountFragment(userPreferences, userManager)
    }
}