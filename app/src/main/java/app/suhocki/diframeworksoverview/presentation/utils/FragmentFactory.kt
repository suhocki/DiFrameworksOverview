package app.suhocki.diframeworksoverview.presentation.utils

import androidx.fragment.app.FragmentFactory
import app.suhocki.diframeworksoverview.di.app.AppComponent
import app.suhocki.diframeworksoverview.di.app.SettingsComponentProxy
import app.suhocki.diframeworksoverview.di.login.LoginComponent
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment

class FragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) = when (className) {
        LoginFragment::class.qualifiedName -> {
            LoginComponent.get().fragment
        }

        AccountFragment::class.qualifiedName -> {
            AppComponent.get().getAccountFragment()
        }

        "app.suhocki.settings.SettingsFragment" -> {
            SettingsComponentProxy.get().getSettingsFragment()
        }

        else -> super.instantiate(classLoader, className)
    }
}