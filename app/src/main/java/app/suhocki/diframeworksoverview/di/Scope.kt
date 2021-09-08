package app.suhocki.diframeworksoverview.di

interface Scope {
    val module: Module

    fun clear() {
        module.clear()
    }
}