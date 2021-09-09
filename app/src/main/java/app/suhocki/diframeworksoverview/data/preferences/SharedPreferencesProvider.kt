package app.suhocki.diframeworksoverview.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.user.UserManager
import javax.inject.Provider
import toothpick.InjectConstructor

@InjectConstructor
class SharedPreferencesProvider(
    private val userManager: UserManager,
    private val context: Context,
) : Provider<SharedPreferences> {

    override fun get(): SharedPreferences {
        val currentUser = userManager.currentUser

        return context.getSharedPreferences(currentUser, Context.MODE_PRIVATE)
    }
}
