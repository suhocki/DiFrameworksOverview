package app.suhocki.diframeworksoverview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginViewModel
import app.suhocki.diframeworksoverview.presentation.login.loginModule
import app.suhocki.diframeworksoverview.presentation.utils.FragmentFactory
import toothpick.ktp.KTP
import toothpick.ktp.extension.getInstance

class MainActivity : AppCompatActivity() {

    init {
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY && !isChangingConfigurations) {
                LoginViewModel.clear()
                KTP.closeScope(MainActivity::class)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragmentFactory()
        initToothpick()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToLogin()
        }
    }

    private fun initToothpick() {
        KTP.openRootScope()
            .openSubScope(MainActivity::class)
            .installModules(globalModule(applicationContext))
    }

    private fun navigateToLogin() {
        val fragment = KTP.openScopes(MainActivity::class, LoginFragment::class)
            .installModules(loginModule())
            .getInstance<LoginFragment>()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}