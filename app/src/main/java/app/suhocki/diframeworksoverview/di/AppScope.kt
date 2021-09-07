package app.suhocki.diframeworksoverview.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager

object AppScope {
    private var application: Application? = null

    val context: Context
        get() = requireNotNull(application?.applicationContext)

    fun init(application: Application) {
        this.application = application
    }

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

    private var loginScope: LoginScope? = null
    private var userScope: UserScope? = null

    fun getOrCreateLoginScope() =
        loginScope ?: LoginScope(this).also { loginScope = it }

    fun openUserScope(userName: String) {
        userScope = UserScope(this, userName)
    }

    fun requireUserScope() = requireNotNull(userScope)

    fun clear() {
        clearLoginScope()
    }

    fun clearLoginScope() {
        loginScope?.clear()
        loginScope = null
    }

    fun clearUserScope() {
        userScope?.clear()
        userScope = null
    }
}