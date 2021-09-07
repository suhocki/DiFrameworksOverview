package app.suhocki.di

import app.suhocki.diframeworksoverview.di.UserScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScopeProvider
import com.google.auto.service.AutoService

@AutoService(SettingsScopeProvider::class)
class SettingsScopeProvider : SettingsScopeProvider {

    override fun getSettingsScope(userScope: UserScope): SettingsScope {
        return SettingsScope(userScope)
    }
}