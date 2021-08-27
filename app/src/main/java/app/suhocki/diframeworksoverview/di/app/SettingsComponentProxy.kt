package app.suhocki.diframeworksoverview.di.app

import androidx.fragment.app.Fragment

interface SettingsComponentProxy {

    fun getSettingsFragment(): Fragment

    companion object {
        fun get(): SettingsComponentProxy =
            Class.forName("app.suhocki.settings.SettingsComponent")
                .getMethod("get")
                .invoke(null) as SettingsComponentProxy
    }
}