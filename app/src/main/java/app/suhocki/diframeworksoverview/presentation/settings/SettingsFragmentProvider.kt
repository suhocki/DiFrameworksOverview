package app.suhocki.diframeworksoverview.presentation.settings

import androidx.fragment.app.Fragment
import java.util.*

interface SettingsFragmentProvider {

    fun getSettingsFragment(): Fragment

    companion object {
        fun get(): SettingsFragmentProvider =
            ServiceLoader.load(SettingsFragmentProvider::class.java).first()
    }
}