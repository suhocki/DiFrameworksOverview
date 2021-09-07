package app.suhocki.diframeworksoverview.presentation.utils

import android.content.Context
import androidx.fragment.app.FragmentFactory
import app.suhocki.diframeworksoverview.di.AppScope
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment

class FragmentFactory(
    private val context: Context
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) = when (className) {
        LoginFragment::class.qualifiedName -> {
            AppScope.getOrCreateLoginScope().loginFragment
        }

        AccountFragment::class.qualifiedName -> {
            AppScope.requireUserScope().accountScope.accountFragment
        }

        "app.suhocki.settings.SettingsFragment" -> {
            AppScope.requireUserScope().settingsScope.settingsFragment
        }

        else -> super.instantiate(classLoader, className)
    }
}