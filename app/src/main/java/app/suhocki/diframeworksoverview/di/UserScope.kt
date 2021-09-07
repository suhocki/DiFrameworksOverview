package app.suhocki.diframeworksoverview.di

import android.content.Context
import android.content.SharedPreferences
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScope
import app.suhocki.diframeworksoverview.presentation.settings.SettingsScopeProvider

class UserScope(
    val appScope: AppScope,
    private val userName: String,
) {
    private var _accountScope: AccountScope? = null

    private var _settingsScope: SettingsScope? = null

    private val sharedPreferences: SharedPreferences
        get() = appScope.context.getSharedPreferences(userName, Context.MODE_PRIVATE)

    val userPreferences: UserPreferences
        get() = UserPreferences(sharedPreferences)

    val accountScope: AccountScope
        get() = _accountScope ?: AccountScope(this).also { _accountScope = it }

    val settingsScope: SettingsScope
        get() = _settingsScope ?: SettingsScopeProvider.get().getSettingsScope(this)
            .also { _settingsScope = it }

}