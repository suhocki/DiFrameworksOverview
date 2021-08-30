package app.suhocki.diframeworksoverview.presentation.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.MainActivity
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.databinding.FragmentAccountBinding
import app.suhocki.diframeworksoverview.di.toScopeID
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.settings.SettingsModuleProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.getKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.get
import java.util.*

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
        val mainActivityScope = getKoin().getScope(MainActivity::class.toScopeID())
        val loginScope = getKoin().createScope<LoginFragment>(LoginFragment::class.toScopeID())
        loginScope.linkTo(mainActivityScope)
        val fragment = loginScope.get<LoginFragment>()

        parentFragmentManager.beginTransaction()
            .remove(this)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun openSettings() {
        val settingsModuleProvider =
            ServiceLoader.load(SettingsModuleProvider::class.java).first()
        loadKoinModules(settingsModuleProvider.getSettingsModule())

        val accountScope = getKoin().getScope(AccountFragment::class.toScopeID())
        val fragmentClass = Class.forName("app.suhocki.settings.SettingsFragment")
        val settingsScope =
            getKoin().getOrCreateScope<MainActivity>(fragmentClass.kotlin.toScopeID())
        settingsScope.linkTo(accountScope)
        val fragment = settingsScope.get<Fragment>(fragmentClass)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("settings")
            .commit()
    }
}