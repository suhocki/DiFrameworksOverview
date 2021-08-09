package app.suhocki.diframeworksoverview.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.databinding.FragmentSettingsBinding
import app.suhocki.diframeworksoverview.domain.preferences.Preferences
import by.kirich1409.viewbindingdelegate.viewBinding

class SettingsFragment(
    private val preferences: Preferences
) : Fragment(R.layout.fragment_settings) {
    private val viewBinding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewBinding.notifications.isChecked = preferences.isNotificationsEnabled
        viewBinding.notifications.setOnCheckedChangeListener { _, isChecked ->
            preferences.isNotificationsEnabled = isChecked
        }
    }
}