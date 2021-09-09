package app.suhocki.diframeworksoverview.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

class UserPreferences(
    private val sharedPreferences: SharedPreferences
) {

    var isNotificationsEnabled: Boolean
        get() = sharedPreferences.getBoolean("isNotificationsEnabled", true)
        set(value) {
            sharedPreferences.edit { putBoolean("isNotificationsEnabled", value) }
        }
}