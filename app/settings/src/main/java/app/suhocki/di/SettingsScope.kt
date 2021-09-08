package app.suhocki.di

import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.settings.SettingsFragment
import com.google.auto.service.AutoService

@AutoService(SettingsScope::class)
class SettingsScope(
    private val userPreferences: UserPreferences
) : SettingsScope {
    override val module = Module()

    inner class Module : SettingsScope.Module {

        override val settingsFragment: Fragment
            get() = SettingsFragment(userPreferences)
    }
}