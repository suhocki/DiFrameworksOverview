package app.suhocki.diframeworksoverview.presentation.settings

import org.koin.core.module.Module
import java.util.*

interface SettingsModuleProvider {

    fun getSettingsModule(): Module

    companion object {
        fun get(): SettingsModuleProvider =
            ServiceLoader.load(SettingsModuleProvider::class.java).first()
    }
}