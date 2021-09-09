package app.suhocki.settings

import app.suhocki.diframeworksoverview.presentation.settings.SettingsModuleProvider
import com.google.auto.service.AutoService
import org.koin.core.module.Module

@AutoService(SettingsModuleProvider::class)
class SettingsModuleProvider : SettingsModuleProvider {

    override fun getSettingsModule(): Module {
        return settingsModule()
    }
}