package app.suhocki.diframeworksoverview.presentation.settings

import androidx.fragment.app.Fragment
import java.util.*

interface SettingsScope {
    val module: Module

    interface Module {
        val settingsFragment: Fragment
    }

    companion object {
        fun get(): SettingsScope =
            ServiceLoader.load(SettingsScope::class.java).first()
    }
}