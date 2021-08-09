package app.suhocki.diframeworksoverview.di.app

import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesWrapper
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import dagger.Binds
import dagger.Module

@Module
abstract class AppBindModule {

    @Binds
    abstract fun bindPreferencesToSharedPreferencesWrapper(
        sharedPreferencesWrapper: SharedPreferencesWrapper
    ): Preferences
}