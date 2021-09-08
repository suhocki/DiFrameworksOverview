package app.suhocki.di.scope

import app.suhocki.di.module.SettingsModule
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import com.google.auto.service.AutoService

@AutoService(SettingsScope::class)
class SettingsScope(userPreferences: UserPreferences) : SettingsScope {
    override val module = SettingsModule(userPreferences)
}