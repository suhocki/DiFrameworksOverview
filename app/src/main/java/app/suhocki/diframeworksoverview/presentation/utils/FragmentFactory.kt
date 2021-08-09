package app.suhocki.diframeworksoverview.presentation.utils

import androidx.fragment.app.FragmentFactory
import app.suhocki.diframeworksoverview.di.app.AppComponent
import app.suhocki.diframeworksoverview.di.login.LoginComponent
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.settings.SettingsFragment

class FragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) = when (className) {
        LoginFragment::class.qualifiedName -> {
            LoginComponent.get().fragment
        }

        AccountFragment::class.qualifiedName -> {
            AppComponent.get().getAccountFragment()
        }

        SettingsFragment::class.qualifiedName -> {
            AppComponent.get().getSettingsFragment()
        }

        else -> super.instantiate(classLoader, className)
    }
}