package app.suhocki.diframeworksoverview.di.scope

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Scope
import app.suhocki.diframeworksoverview.di.ScopeHolder
import app.suhocki.diframeworksoverview.di.module.AppModule

@SuppressLint("StaticFieldLeak")
object AppScope : Scope {
    private var application: Application? = null

    override val module: AppModule by lazy {
        AppModule(requireNotNull(application))
    }

    val scopes by lazy {
        with(module) { Scopes(userManager, context, errorHandler) }
    }

    fun init(application: Application) {
        this.application = application
    }

    class Scopes(
        private val userManager: UserManager,
        private val context: Context,
        private val errorHandler: ErrorHandler,
    ) {
        val login = LoginScopeHolder()
        val user = UserScopeHolder()

        inner class LoginScopeHolder : ScopeHolder<LoginScope>() {
            override fun create() {
                scope = LoginScope(context, errorHandler, userManager)
            }
        }

        inner class UserScopeHolder : ScopeHolder<UserScope>() {
            private var userName: String? = null

            fun create(userName: String) {
                this.userName = userName
                create()
            }

            override fun create() {
                scope = UserScope(userManager, context, requireNotNull(userName))
            }

            override fun clear() {
                super.clear()
                userName = null
            }
        }
    }
}