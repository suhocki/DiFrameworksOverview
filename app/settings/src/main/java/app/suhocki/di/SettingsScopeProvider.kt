package app.suhocki.di

import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScopeProvider
import com.google.auto.service.AutoService

@AutoService(SettingsScopeProvider::class)
class SettingsScopeProvider : SettingsScopeProvider {

    override fun getSettingsScope(userPreferences: UserPreferences): SettingsScope {
        return SettingsScope(userPreferences)
    }
}