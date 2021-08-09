package app.suhocki.diframeworksoverview.presentation.utils.mvvm

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.data.authorization.AuthorizationApi
import app.suhocki.diframeworksoverview.data.authorization.LoginRepository
import app.suhocki.diframeworksoverview.data.device.DeviceController
import app.suhocki.diframeworksoverview.data.error.ErrorHandler
import app.suhocki.diframeworksoverview.data.security.SecurityManager
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.presentation.login.LoginViewModel

object ViewModelStorage {
    val storage = mutableMapOf<String, Any>()

    inline fun <reified T> getViewModel(context: Context): T {
        val key = requireNotNull(T::class.qualifiedName)

        return storage.getOrElse(key) {
            val viewModel = createViewModel<T>(context)

            storage[key] = viewModel as Any

            viewModel
        } as T
    }

    fun clearAll() {
        storage.clear()
    }

    inline fun <reified T> clear() {
        storage.remove(T::class.qualifiedName)
    }

    inline fun <reified T> createViewModel(context: Context): T {
        return when (T::class) {

            LoginViewModel::class -> {
                val errorHandler = ErrorHandler()
                val sharedPreferences = createEncryptedSharedPreferences(context)
                val userManager = UserManager(sharedPreferences)
                val authorizationApi = AuthorizationApi()
                val contentResolver = obtainContentResolver(context)
                val deviceController = DeviceController(contentResolver)
                val securityManager = SecurityManager(deviceController)
                val loginRepository = LoginRepository(authorizationApi, securityManager)

                LoginViewModel(errorHandler, userManager, loginRepository) as T
            }

            else -> error("ViewModel for ${T::class.qualifiedName} cannot be created.")
        }
    }

    fun createEncryptedSharedPreferences(context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "encrypted_preferences",
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun obtainContentResolver(context: Context): ContentResolver {
        return context.contentResolver
    }
}
