package app.suhocki.diframeworksoverview.di.app

import android.content.Context
import app.suhocki.diframeworksoverview.di.login.LoginComponent
import app.suhocki.diframeworksoverview.presentation.account.AccountFragment
import app.suhocki.diframeworksoverview.presentation.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun getLoginComponent(): LoginComponent.Builder

    fun getAccountFragment(): AccountFragment

    fun getSettingsFragment(): SettingsFragment

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context)

        fun build(): AppComponent
    }

    companion object {
        private var instance: AppComponent? = null

        fun initialize(context: Context) {
            instance ?: run {
                instance = DaggerAppComponent.builder()
                    .apply { context(context) }
                    .build()
            }
        }

        fun get() = requireNotNull(instance)
    }
}