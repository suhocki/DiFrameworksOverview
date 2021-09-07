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
    private lateinit var userScope: UserScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userScope = AppScope.requireUserScope()
    }

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
        if (isRemoving) {
            userScope.clearAccountScope()
        }
    }

    private fun openLogin() {
        AppScope.clearUserScope()

        val fragment = AppScope.getOrCreateLoginScope().loginFragment

        parentFragmentManager.beginTransaction()
            .remove(this)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun openSettings() {
        val fragment = AppScope.requireUserScope().settingsScope.settingsFragment

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("settings")
            .commit()
    }
}