package app.suhocki.diframeworksoverview.data.user

import android.content.SharedPreferences
import androidx.core.content.edit

class UserManager(
    private val sharedPreferences: SharedPreferences
) {
    var currentUser: String?
        get() = sharedPreferences.getString(KEY_CURRENT_USER, null)
        set(value) {
            sharedPreferences.edit { putString(KEY_CURRENT_USER, value) }
        }

    var securityToken: String?
        get() = sharedPreferences.getString(KEY_SECURITY_TOKEN, null)
        set(value) {
            sharedPreferences.edit { putString(KEY_SECURITY_TOKEN, value) }
        }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val KEY_CURRENT_USER = "currentUser"
        private const val KEY_SECURITY_TOKEN = "securityToken"
    }
}