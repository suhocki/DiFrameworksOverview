package app.suhocki.di.module

import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.settings.SettingsFragment

class SettingsModule(
    private val userPreferences: UserPreferences
) : SettingsScope.SettingsModule {

    override val settingsFragment: Fragment
        get() = SettingsFragment(userPreferences)
}