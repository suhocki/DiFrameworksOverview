package app.suhocki.diframeworksoverview.presentation.settings

import org.koin.core.module.Module

fun settingsModule(): Module = Class.forName("app.suhocki.settings.SettingsModuleKt")
    .getMethod("settingsModule")
    .invoke(null) as Module