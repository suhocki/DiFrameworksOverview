package app.suhocki.settings

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

fun settingsModule() = DI.Module(name = "settings") {
    bind(SettingsFragment::class.qualifiedName) { provider { SettingsFragment(instance()) } }
}
