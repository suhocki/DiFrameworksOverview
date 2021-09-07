package app.suhocki.diframeworksoverview

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.suhocki.diframeworksoverview.di.AppScope
import app.suhocki.diframeworksoverview.presentation.utils.FragmentFactory

class MainActivity : AppCompatActivity() {

    init {
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY && !isChangingConfigurations) {
                AppScope.clearLoginScope()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragmentFactory(applicationContext)
        initDI()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToLogin()
        }
    }

    private fun initDI() {
        AppScope.init(applicationContext as Application)
    }

    private fun navigateToLogin() {
        val fragment = AppScope.getOrCreateLoginScope().loginFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}