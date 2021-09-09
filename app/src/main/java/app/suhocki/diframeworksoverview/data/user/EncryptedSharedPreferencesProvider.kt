package app.suhocki.diframeworksoverview.data.user

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class EncryptedSharedPreferencesProvider(
    private val context: Context,
) : Provider<SharedPreferences> {

    override fun get(): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    companion object {
        private const val FILE_NAME = "encrypted_preferences"
    }
}
