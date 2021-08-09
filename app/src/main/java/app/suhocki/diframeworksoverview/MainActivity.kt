package app.suhocki.diframeworksoverview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.suhocki.diframeworksoverview.di.app.AppComponent
import app.suhocki.diframeworksoverview.di.login.LoginComponent
import app.suhocki.diframeworksoverview.presentation.utils.FragmentFactory

class MainActivity : AppCompatActivity() {

    init {
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY && !isChangingConfigurations) {
                LoginComponent.destroy()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragmentFactory()
        AppComponent.initialize(applicationContext)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        LoginComponent.initialize()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginComponent.get().fragment)
            .commit()
    }
}