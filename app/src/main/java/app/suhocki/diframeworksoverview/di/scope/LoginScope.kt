package app.suhocki.diframeworksoverview.di.scope

import android.content.Context
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Scope
import app.suhocki.diframeworksoverview.di.module.LoginModule

class LoginScope(
    context: Context,
    errorHandler: ErrorHandler,
    userManager: UserManager,
) : Scope {
    override val module = LoginModule(context, errorHandler, userManager)
}