package app.suhocki.diframeworksoverview.presentation.settings

import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.di.Module
import app.suhocki.diframeworksoverview.di.Scope
import java.util.*

interface SettingsScope : Scope {
    override val module: SettingsModule

    interface SettingsModule : Module {
        val settingsFragment: Fragment
    }

    companion object {
        fun get(): SettingsScope =
            ServiceLoader.load(SettingsScope::class.java).first()
    }
}