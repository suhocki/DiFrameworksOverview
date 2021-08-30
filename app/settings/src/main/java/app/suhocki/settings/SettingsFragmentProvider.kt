package app.suhocki.settings

import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.di.app.SettingsComponentProxy
import org.kohsuke.MetaInfServices

@MetaInfServices
class SettingsFragmentProvider : SettingsComponentProxy {
    override fun getSettingsFragment(): Fragment {
        return SettingsComponent.get().getSettingsFragment()
    }
}