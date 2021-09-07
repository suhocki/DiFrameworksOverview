package app.suhocki.diframeworksoverview.presentation.settings

import app.suhocki.diframeworksoverview.di.UserScope
import java.util.*

interface SettingsScopeProvider {

    fun getSettingsScope(userScope: UserScope): SettingsScope

    companion object {
        fun get(): SettingsScopeProvider =
            ServiceLoader.load(SettingsScopeProvider::class.java).first()
    }
}