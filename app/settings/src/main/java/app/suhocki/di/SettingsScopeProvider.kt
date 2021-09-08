package app.suhocki.di

import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScopeProvider
import com.google.auto.service.AutoService
import app.suhocki.di.scope.SettingsScope as SettingsScopeImpl

@AutoService(SettingsScopeProvider::class)
class SettingsScopeProvider : SettingsScopeProvider {

    override fun getSettingsScope(userPreferences: UserPreferences): SettingsScope {
        return SettingsScopeImpl(userPreferences)
    }
}