package app.suhocki.diframeworksoverview.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import toothpick.InjectConstructor

@InjectConstructor
class SharedPreferencesWrapper(
    private val sharedPreferences: SharedPreferences
) : Preferences {

    override var isNotificationsEnabled: Boolean
        get() = sharedPreferences.getBoolean("isNotificationsEnabled", true)
        set(value) {
            sharedPreferences.edit { putBoolean("isNotificationsEnabled", value) }
        }
}