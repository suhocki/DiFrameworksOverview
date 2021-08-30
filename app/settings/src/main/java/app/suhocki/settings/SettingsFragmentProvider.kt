package app.suhocki.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesWrapper
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.presentation.settings.SettingsFragmentProvider
import com.google.auto.service.AutoService

@AutoService(SettingsFragmentProvider::class)
class SettingsFragmentProvider : SettingsFragmentProvider {

    override fun getSettingsFragment(context: Context): Fragment {
        val encryptedSharedPreferences = createEncryptedSharedPreferences(context)
        val userManager = UserManager(encryptedSharedPreferences)
        val currentUser = userManager.currentUser
        val sharedPreferences = context.getSharedPreferences(currentUser, Context.MODE_PRIVATE)
        val preferences = SharedPreferencesWrapper(sharedPreferences)
        return SettingsFragment(preferences)
    }

    private fun createEncryptedSharedPreferences(context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "encrypted_preferences",
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}