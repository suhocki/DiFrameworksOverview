package app.suhocki.settings

import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.presentation.settings.SettingsFragmentProvider
import com.google.auto.service.AutoService

@AutoService(SettingsFragmentProvider::class)
class SettingsFragmentProvider : SettingsFragmentProvider {

    override fun getSettingsFragment(): Fragment {
        return SettingsComponent.get().getSettingsFragment()
    }
}