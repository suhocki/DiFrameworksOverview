package app.suhocki.settings

import app.suhocki.diframeworksoverview.di.app.AppComponent
import dagger.Component

@Component(dependencies = [AppComponent::class])
interface SettingsComponent {

    fun getSettingsFragment(): SettingsFragment

    companion object {
        private var instance: SettingsComponent? = null

        @JvmStatic
        fun get() = instance ?: run {
            return DaggerSettingsComponent.builder()
                .appComponent(AppComponent.get())
                .build().apply {
                    instance = this
                }
        }
    }
}