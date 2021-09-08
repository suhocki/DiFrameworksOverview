package app.suhocki.diframeworksoverview.presentation.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.databinding.FragmentAccountBinding
import app.suhocki.diframeworksoverview.di.AppScope
import app.suhocki.diframeworksoverview.di.UserScope
import by.kirich1409.viewbindingdelegate.viewBinding

class AccountFragment(
    private val userPreferences: UserPreferences,
    private val userManager: UserManager,
) : Fragment(R.layout.fragment_account) {

    private val viewBinding by viewBinding<FragmentAccountBinding>()

    private val userScope: UserScope
        get() = AppScope.scopes.user.get()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            notifications.isEnabled = userPreferences.isNotificationsEnabled
            logout.setOnClickListener { openLogin() }
            settings.setOnClickListener { openSettings() }
            greeting.text = "Hello, ${userManager.currentUser}!"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!requireActivity().isChangingConfigurations) {
            if (AppScope.scopes.user.isOpen()) {
                userScope.scopes.account.clear()
            }
        }
    }

    private fun openLogin() {
        val loginScope = with(AppScope.scopes) {
            user.clear()
            login.create()
            login.get()
        }

        val fragment = loginScope.module.loginFragment

        parentFragmentManager.beginTransaction()
            .remove(this)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun openSettings() {
        val settingsScope = with(userScope.scopes.settings) {
            create()
            get()
        }

        val fragment = settingsScope.module.settingsFragment

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("settings")
            .commit()
    }
}