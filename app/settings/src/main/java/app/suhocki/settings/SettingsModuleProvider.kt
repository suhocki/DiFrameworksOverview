package app.suhocki.settings

import app.suhocki.diframeworksoverview.presentation.settings.SettingsModuleProvider
import com.google.auto.service.AutoService
import org.kodein.di.DI

@AutoService(SettingsModuleProvider::class)
class SettingsModuleProvider : SettingsModuleProvider {

    override fun getSettingsModule(): DI.Module {
        return settingsModule()
    }
}