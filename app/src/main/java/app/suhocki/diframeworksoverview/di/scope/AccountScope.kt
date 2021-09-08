package app.suhocki.diframeworksoverview.di.scope

import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Scope
import app.suhocki.diframeworksoverview.di.module.AccountModule

class AccountScope(
    userPreferences: UserPreferences,
    userManager: UserManager
) : Scope {
    override val module = AccountModule(userPreferences, userManager)
}