package app.suhocki.diframeworksoverview.presentation.settings

import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.domain.preferences.Preferences

object SettingsFragmentProxy {
    private val CONSTRUCTOR_SIGNATURE = Preferences::class.java
    val QUALIFIED_NAME = "app.suhocki.settings.SettingsFragment"

    fun create(preferences: Preferences): Fragment {
        return Class.forName(QUALIFIED_NAME)
            .getConstructor(CONSTRUCTOR_SIGNATURE)
            .newInstance(preferences) as Fragment
    }
}