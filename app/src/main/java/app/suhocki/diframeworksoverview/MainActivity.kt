package app.suhocki.diframeworksoverview

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.suhocki.diframeworksoverview.di.AppScope
import app.suhocki.diframeworksoverview.presentation.utils.FragmentFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragmentFactory()
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
        val loginScope = with(AppScope.scopes.login) {
            create()
            get()
        }

        val fragment = loginScope.module.loginFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}