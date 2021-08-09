package app.suhocki.diframeworksoverview.di.app

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.user.UserManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(userManager: UserManager, context: Context): SharedPreferences {
        val currentUser = userManager.currentUser

        return context.getSharedPreferences(currentUser, Context.MODE_PRIVATE)
    }

    @Provides
    @Named("encrypted")
    fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_PREFERENCES_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    companion object {
        private const val ENCRYPTED_PREFERENCES_FILE = "encrypted_preferences"
    }
}