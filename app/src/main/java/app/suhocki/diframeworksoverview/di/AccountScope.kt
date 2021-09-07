package app.suhocki.diframeworksoverview.di

import app.suhocki.diframeworksoverview.presentation.account.AccountFragment

class AccountScope(private val userScope: UserScope) {

    val accountFragment: AccountFragment
        get() = AccountFragment(userScope.userPreferences, userScope.appScope.userManager)
}