package app.suhocki.settings

import app.suhocki.diframeworksoverview.MainActivity
import org.koin.dsl.factory
import org.koin.dsl.module

fun settingsModule() = module {
    scope<MainActivity> {
        factory<SettingsFragment>()
    }
}