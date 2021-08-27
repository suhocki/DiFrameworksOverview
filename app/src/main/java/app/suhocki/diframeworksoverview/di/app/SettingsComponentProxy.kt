package app.suhocki.diframeworksoverview.di.app

import androidx.fragment.app.Fragment

object SettingsComponentProxy {
    private val componentClass: Class<*> = Class.forName("app.suhocki.settings.SettingsComponent")

    fun getSettingsFragment(): Fragment {
        val appComponent = AppComponent.get()

        componentClass
            .getMethod("initialize", AppComponent::class.java)
            .invoke(null, appComponent)

        val settingComponent = componentClass
            .getMethod("get")
            .invoke(null) as Any

        return componentClass
            .getMethod("getSettingsFragment")
            .invoke(settingComponent) as Fragment
    }
}