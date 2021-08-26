package app.suhocki.diframeworksoverview.presentation.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesWrapper
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.databinding.FragmentAccountBinding
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginViewModel
import app.suhocki.diframeworksoverview.presentation.settings.SettingsFragmentProxy
import app.suhocki.diframeworksoverview.presentation.utils.mvvm.ViewModelStorage
import by.kirich1409.viewbindingdelegate.viewBinding

class AccountFragment(
    private val preferences: Preferences,
    private val userManager: UserManager,
) : Fragment(R.layout.fragment_account) {

    private val viewBinding by viewBinding<FragmentAccountBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.notifications.isEnabled = preferences.isNotificationsEnabled

        viewBinding.logout.setOnClickListener {
            userManager.clear()
            openLogin()
        }

        viewBinding.settings.setOnClickListener {
            openSettings()
        }

        viewBinding.greeting.text = "Hello, ${userManager.currentUser}!"
    }

    private fun openLogin() {
        val viewModel: LoginViewModel =
            ViewModelStorage.getViewModel(requireContext().applicationContext)
        val fragment = LoginFragment(viewModel)

        parentFragmentManager.beginTransaction()
            .remove(this)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun openSettings() {
        val encryptedSharedPreferences = createEncryptedSharedPreferences()
        val userManager = UserManager(encryptedSharedPreferences)
        val currentUser = userManager.currentUser
        val sharedPreferences = requireContext().getSharedPreferences(currentUser, Context.MODE_PRIVATE)
        val preferences = SharedPreferencesWrapper(sharedPreferences)
        val fragment = SettingsFragmentProxy.create(preferences)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("settings")
            .commit()
    }

    private fun createEncryptedSharedPreferences(): SharedPreferences {
        return EncryptedSharedPreferences.create(
            requireContext(),
            "encrypted_preferences",
            MasterKey.Builder(requireContext())
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}