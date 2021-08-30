package app.suhocki.diframeworksoverview.presentation.settings

import android.content.Context
import androidx.fragment.app.Fragment
import java.util.*

interface SettingsFragmentProvider {

    fun getSettingsFragment(context: Context): Fragment

    companion object {
        fun get(): SettingsFragmentProvider =
            ServiceLoader.load(SettingsFragmentProvider::class.java).first()
    }
}