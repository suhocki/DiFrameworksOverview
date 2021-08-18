package app.suhocki.diframeworksoverview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.suhocki.diframeworksoverview.di.toScopeID
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import app.suhocki.diframeworksoverview.presentation.login.loginModule
import app.suhocki.diframeworksoverview.presentation.utils.FragmentFactory
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    init {
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY && !isChangingConfigurations) {
                getKoin().getScope(MainActivity::class.toScopeID()).close()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragmentFactory()
        initKoin()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToLogin()
        }
    }

    private fun initKoin() {
        if (GlobalContext.getOrNull() != null) {
            return
        }

        startKoin {
            androidContext(applicationContext)
            modules(mainModule())
        }

        getKoin().createScope<MainActivity>(MainActivity::class.toScopeID())
    }

    private fun navigateToLogin() {
        loadKoinModules(loginModule())

        val mainScope = getKoin().getScope(MainActivity::class.toScopeID())
        val loginScope = getKoin().createScope<LoginFragment>(LoginFragment::class.toScopeID())

        loginScope.linkTo(mainScope)

        val fragment = loginScope.get<LoginFragment>()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}