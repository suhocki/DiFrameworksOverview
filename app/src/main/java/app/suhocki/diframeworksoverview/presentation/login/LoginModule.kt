package app.suhocki.diframeworksoverview.presentation.login

import org.koin.dsl.factory
import org.koin.dsl.module
import org.koin.dsl.scoped

fun loginModule() = module {
    scope<LoginFragment> {
        scoped<LoginViewModel>()

        factory<LoginFragment>()
    }
}