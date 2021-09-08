package app.suhocki.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.data.preferences.UserPreferences
import app.suhocki.diframeworksoverview.databinding.FragmentSettingsBinding
import app.suhocki.diframeworksoverview.di.scope.AppScope
import app.suhocki.diframeworksoverview.di.scope.UserScope
import by.kirich1409.viewbindingdelegate.viewBinding

class SettingsFragment(
    private val userPreferences: UserPreferences
) : Fragment(R.layout.fragment_settings) {
    private val viewBinding: FragmentSettingsBinding by viewBinding()

    private val userScope: UserScope
        get() = AppScope.scopes.user.get()

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

    override fun onDestroy() {
        super.onDestroy()
        if (!requireActivity().isChangingConfigurations) {
            userScope.scopes.settings.clear()
        }
    }
}