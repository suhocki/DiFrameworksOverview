package app.suhocki.diframeworksoverview.di.login

import app.suhocki.diframeworksoverview.di.app.AppComponent
import app.suhocki.diframeworksoverview.presentation.login.LoginFragment
import dagger.Subcomponent

@LoginScope
@Subcomponent
interface LoginComponent {

    val fragment: LoginFragment

    @Subcomponent.Builder
    interface Builder {

        fun build(): LoginComponent
    }

    companion object {
        private var instance: LoginComponent? = null

        fun initialize() {
            instance ?: run {
                instance = AppComponent.get().getLoginComponent().build()
            }
        }

        fun get() = requireNotNull(instance)

        fun destroy() {
            instance = null
        }
    }
}