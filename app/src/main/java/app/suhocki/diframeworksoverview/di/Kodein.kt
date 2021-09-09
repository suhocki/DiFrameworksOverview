package app.suhocki.diframeworksoverview.di

import org.kodein.di.DI

object Kodein {
    private lateinit var kodein: DI

    val instance: DI
        get() = kodein

    fun addModule(module: DI.Module) {
        kodein = DI {
            if (::kodein.isInitialized) {
                extend(kodein)
            }
            importOnce(module)
        }
    }
}