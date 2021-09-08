package app.suhocki.diframeworksoverview.di

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager

@SuppressLint("StaticFieldLeak")
object AppScope {
    private var module: Module? = null

    val scopes by lazy {
        with(requireNotNull(module)) {
            Scopes(userManager, context, errorHandler)
        }
    }

    fun init(application: Application) {
        module = Module(application)
    }

    class Module(
        private val application: Application
    ) {
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

        inner class Login {
            private var loginScope: LoginScope? = null

            fun create() {
                loginScope = LoginScope(context, errorHandler, userManager)
            }

            fun get() = requireNotNull(loginScope)

            fun clear() {
                loginScope?.clear()
                loginScope = null
            }
        }

        inner class User {
            private var userScope: UserScope? = null

            fun isOpen() = userScope != null

            fun create(userName: String) {
                userScope = UserScope(userManager, context, userName)
            }

            fun get() = requireNotNull(userScope)

            fun clear() {
                userScope?.clear()
                userScope = null
            }
        }
    }
}