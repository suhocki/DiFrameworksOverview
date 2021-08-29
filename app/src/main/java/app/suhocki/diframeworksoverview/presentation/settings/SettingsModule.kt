package app.suhocki.diframeworksoverview.presentation.settings

import org.kodein.di.DI

fun settingsModule(): DI.Module = Class.forName("app.suhocki.settings.SettingsModuleKt")
    .getMethod("settingsModule")
    .invoke(null) as DI.Module