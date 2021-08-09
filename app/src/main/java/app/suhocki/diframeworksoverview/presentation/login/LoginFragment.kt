package app.suhocki.diframeworksoverview.presentation.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import app.suhocki.diframeworksoverview.MainActivity
import app.suhocki.diframeworksoverview.R
import app.suhocki.diframeworksoverview.databinding.FragmentLoginBinding
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.utils.showSnackbar
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import toothpick.InjectConstructor
import toothpick.ktp.KTP
import toothpick.ktp.extension.getInstance

@InjectConstructor
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
        val fragment = KTP.openScopes(MainActivity::class, AccountFragment::class)
            .getInstance<AccountFragment>()

        parentFragmentManager.beginTransaction()
            .remove(this)
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRemoving) {
            KTP.closeScope(LoginFragment::class)
        }
    }

    data class ScreenState(
        val isProgress: Boolean = false,
        val isLoginCompleted: Boolean = false
    )
}