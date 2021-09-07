package app.suhocki.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.databinding.FragmentSettingsBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class SettingsFragment(
    private val userPreferences: UserPreferences
) : Fragment(R.layout.fragment_settings) {
    private val viewBinding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewBinding.notifications.isChecked = userPreferences.isNotificationsEnabled
        viewBinding.notifications.setOnCheckedChangeListener { _, isChecked ->
            userPreferences.isNotificationsEnabled = isChecked
        }
    }
}