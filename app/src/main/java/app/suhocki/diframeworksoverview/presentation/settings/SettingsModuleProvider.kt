package app.suhocki.diframeworksoverview.presentation.settings

import org.kodein.di.DI
import java.util.*

interface SettingsModuleProvider {

    fun getSettingsModule(): DI.Module

    companion object {
        fun get(): SettingsModuleProvider =
            ServiceLoader.load(SettingsModuleProvider::class.java).first()
    }
}