package app.suhocki.diframeworksoverview.di.module

import android.content.Context
import android.content.SharedPreferences
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.di.Module

class UserModule(
    private val context: Context,
    private val userName: String,
) : Module {
    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(userName, Context.MODE_PRIVATE)

    val userPreferences: UserPreferences
        get() = UserPreferences(sharedPreferences)
}