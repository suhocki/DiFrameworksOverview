package app.suhocki.diframeworksoverview.presentation.settings

import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import java.util.*

interface SettingsScopeProvider {

    fun getSettingsScope(userPreferences: UserPreferences): SettingsScope

    companion object {
        fun get(): SettingsScopeProvider =
            ServiceLoader.load(SettingsScopeProvider::class.java).first()
    }
}