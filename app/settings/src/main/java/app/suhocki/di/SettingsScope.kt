package app.suhocki.di

import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.di.UserScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.settings.SettingsFragment
import com.google.auto.service.AutoService

@AutoService(SettingsScope::class)
class SettingsScope(
    private val userScope: UserScope
) : SettingsScope {

    private val userPreferences: UserPreferences
        get() = userScope.userPreferences

    override val settingsFragment: Fragment
        get() = SettingsFragment(userPreferences)
}