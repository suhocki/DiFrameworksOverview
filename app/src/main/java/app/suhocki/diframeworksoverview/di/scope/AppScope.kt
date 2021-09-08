package app.suhocki.diframeworksoverview.di.scope

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Module
import app.suhocki.diframeworksoverview.di.Scope
import app.suhocki.diframeworksoverview.di.ScopeHolder

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

    class AppModule(
        private val application: Application
    ) : Module {
        val context: Context
            get() = requireNotNull(application.applicationContext)

        val errorHandler: ErrorHandler
            get() = ErrorHandler()

        private val sharedPreferences: SharedPreferences
            get() = EncryptedSharedPreferences.create(
                context,
                "encrypted_preferences",
                MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

        val userManager: UserManager
            get() = UserManager(sharedPreferences)
    }

    class Scopes(
        private val userManager: UserManager,
        private val context: Context,
        private val errorHandler: ErrorHandler,
    ) {
        val login = Login()
        val user = User()

        inner class Login : ScopeHolder<LoginScope>() {
            override fun create() {
                scope = LoginScope(context, errorHandler, userManager)
            }
        }

        inner class User : ScopeHolder<UserScope>() {
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