package app.suhocki.diframeworksoverview.presentation.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.databinding.FragmentAccountBinding
import app.suhocki.diframeworksoverview.di.Kodein
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginScope
import app.suhocki.diframeworksoverview.presentation.settings.settingsModule
import by.kirich1409.viewbindingdelegate.viewBinding
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.on

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
        val kodein = Kodein.instance.on(LoginScope)
        val fragment = kodein.direct.instance<Fragment>(LoginFragment::class.qualifiedName)

        parentFragmentManager.beginTransaction()
            .remove(this)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun openSettings() {
        Kodein.addModule(settingsModule())

        val fragment = Kodein.instance.direct
            .instance<Fragment>("app.suhocki.settings.SettingsFragment")

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("settings")
            .commit()
    }
}