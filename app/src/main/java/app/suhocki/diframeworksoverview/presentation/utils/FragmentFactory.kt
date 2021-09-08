package app.suhocki.diframeworksoverview.presentation.utils

import androidx.fragment.app.FragmentFactory
import app.suhocki.diframeworksoverview.di.AppScope
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment

class FragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) = when (className) {
        LoginFragment::class.qualifiedName -> {
            AppScope.scopes.login.get().module.loginFragment
        }

        AccountFragment::class.qualifiedName -> {
            AppScope.scopes.user.get().scopes.account.get().module.accountFragment
        }

        "app.suhocki.settings.SettingsFragment" -> {
            AppScope.scopes.user.get().scopes.settings.get().module.settingsFragment
        }

        else -> super.instantiate(classLoader, className)
    }
}