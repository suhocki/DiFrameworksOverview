package app.suhocki.diframeworksoverview.data.user

import android.content.SharedPreferences
import androidx.core.content.edit
import toothpick.InjectConstructor
import javax.inject.Named

@InjectConstructor
class UserManager(
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