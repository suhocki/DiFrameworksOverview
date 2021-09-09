package app.suhocki.diframeworksoverview.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.di.Module

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