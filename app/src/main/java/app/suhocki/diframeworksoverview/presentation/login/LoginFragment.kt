package app.suhocki.diframeworksoverview.presentation.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.data.preferences.SharedPreferencesWrapper
import app.suhocki.diframeworksoverview.data.user.UserManager
import app.suhocki.diframeworksoverview.databinding.FragmentLoginBinding
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.utils.mvvm.ViewModelStorage
import app.suhocki.diframeworksoverview.presentation.utils.showSnackbar
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment(
    private val viewModel: LoginViewModel
) : Fragment(R.layout.fragment_login) {
    private val viewBinding by viewBinding<FragmentLoginBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.errorHandler.errorString
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { text -> showSnackbar(text) }
        }

        lifecycleScope.launch {
            viewModel.screenState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { (isProgress, isLoginCompleted) ->
                    viewBinding.progress.isVisible = isProgress
                    if (isLoginCompleted) {
                        navigateToAccount()
                    }
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.login.setOnClickListener {
            val user = viewBinding.user.text.toString()
            viewModel.login(user)
        }
    }

    private fun navigateToAccount() {
        val encryptedSharedPreferences = createEncryptedSharedPreferences()
        val userManager = UserManager(encryptedSharedPreferences)
        val currentUser = userManager.currentUser
        val sharedPreferences = requireContext().getSharedPreferences(currentUser, Context.MODE_PRIVATE)
        val preferences = SharedPreferencesWrapper(sharedPreferences)
        val fragment = AccountFragment(preferences, userManager)

        parentFragmentManager.beginTransaction()
            .remove(this)
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRemoving) {
            ViewModelStorage.clear<LoginViewModel>()
        }
    }

    private fun createEncryptedSharedPreferences(): SharedPreferences {
        return EncryptedSharedPreferences.create(
            requireContext(),
            "encrypted_preferences",
            MasterKey.Builder(requireContext())
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    data class ScreenState(
        val isProgress: Boolean = false,
        val isLoginCompleted: Boolean = false
    )
}