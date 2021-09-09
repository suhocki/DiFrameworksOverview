package app.suhocki.diframeworksoverview

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesProvider
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesWrapper
import app.suhocki.diframeworksoverview.data.user.EncryptedSharedPreferencesProvider
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun globalModule(context: Context) = module {
    bind<Context>().toInstance(context)
    bind<ContentResolver>().toInstance(context.contentResolver)

    bind<SharedPreferences>().toProvider(SharedPreferencesProvider::class)
    bind<Preferences>().toClass<SharedPreferencesWrapper>()

    bind<SharedPreferences>().withName("encrypted").toProvider(EncryptedSharedPreferencesProvider::class)
}