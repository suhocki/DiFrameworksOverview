package app.suhocki.diframeworksoverview.data.preferences

import android.content.Context
import android.content.SharedPreferences
import app.suhocki.diframeworksoverview.data.user.UserManager

class SharedPreferencesProvider(
    private val userManager: UserManager,
    private val context: Context,
) {

    fun get(): SharedPreferences {
        val currentUser = userManager.currentUser

        return context.getSharedPreferences(currentUser, Context.MODE_PRIVATE)
    }
}