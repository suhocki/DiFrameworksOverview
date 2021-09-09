package app.suhocki.diframeworksoverview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.suhocki.diframeworksoverview.di.Kodein
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.LoginScope
import app.suhocki.diframeworksoverview.presentation.login.loginModule
import app.suhocki.diframeworksoverview.presentation.utils.FragmentFactory
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.on

class MainActivity : AppCompatActivity() {

    init {
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY && !isChangingConfigurations) {
                LoginScope.close()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragmentFactory()
        initKodein()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToLogin()
        }
    }

    private fun initKodein() {
        Kodein.addModule(mainModule(applicationContext))
    }

    private fun navigateToLogin() {
        Kodein.addModule(loginModule())

        val kodein = Kodein.instance.on(LoginScope)
        val fragment = kodein.direct.instance<Fragment>(LoginFragment::class.qualifiedName)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}