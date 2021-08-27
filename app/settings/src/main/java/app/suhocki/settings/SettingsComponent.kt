package app.suhocki.settings

import app.suhocki.diframeworksoverview.di.app.AppComponent
import dagger.Component

@Component(dependencies = [AppComponent::class])
interface SettingsComponent {

    fun getSettingsFragment(): SettingsFragment

    companion object {
        private var instance: SettingsComponent? = null

        @JvmStatic
        fun initialize(appComponent: AppComponent) {
            instance ?: run {
                instance = DaggerSettingsComponent.builder()
                    .appComponent(appComponent)
                    .build()
            }
        }

        @JvmStatic
        fun get() = requireNotNull(instance)
    }
}