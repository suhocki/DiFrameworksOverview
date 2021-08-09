package app.suhocki.diframeworksoverview.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentFactory
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesWrapper
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginViewModel
import app.suhocki.diframeworksoverview.presentation.settings.SettingsFragment
import app.suhocki.diframeworksoverview.presentation.utils.mvvm.ViewModelStorage

class FragmentFactory(
    private val context: Context
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) = when (className) {
        LoginFragment::class.qualifiedName -> {
            val viewModel: LoginViewModel = ViewModelStorage.getViewModel(context)
            LoginFragment(viewModel)
        }

        AccountFragment::class.qualifiedName -> {
            val encryptedSharedPreferences = createEncryptedSharedPreferences()
            val userManager = UserManager(encryptedSharedPreferences)
            val currentUser = userManager.currentUser
            val sharedPreferences = context.getSharedPreferences(currentUser, Context.MODE_PRIVATE)
            val preferences = SharedPreferencesWrapper(sharedPreferences)
            AccountFragment(preferences, userManager)
        }

        SettingsFragment::class.qualifiedName -> {
            val encryptedSharedPreferences = createEncryptedSharedPreferences()
            val userManager = UserManager(encryptedSharedPreferences)
            val currentUser = userManager.currentUser
            val sharedPreferences = context.getSharedPreferences(currentUser, Context.MODE_PRIVATE)
            val preferences = SharedPreferencesWrapper(sharedPreferences)
            SettingsFragment(preferences)
        }

        else -> super.instantiate(classLoader, className)
    }

    private fun createEncryptedSharedPreferences(): SharedPreferences {
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