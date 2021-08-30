package app.suhocki.diframeworksoverview.di.app

import androidx.fragment.app.Fragment
import java.util.*

interface SettingsComponentProxy {

    fun getSettingsFragment(): Fragment

    companion object {
        fun get(): SettingsComponentProxy =
            ServiceLoader.load(SettingsComponentProxy::class.java).first()
    }
}