package app.suhocki.diframeworksoverview.data.user

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Named

class UserManager @Inject constructor(
    @Named("encrypted") private val sharedPreferences: SharedPreferences
) {
    var currentUser: String?
        get() = sharedPreferences.getString("currentUser", null)
        set(value) {
            sharedPreferences.edit { putString("currentUser", value) }
        }

    var securityToken: String?
        get() = sharedPreferences.getString("securityToken", null)
        set(value) {
            sharedPreferences.edit { putString("securityToken", value) }
        }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}