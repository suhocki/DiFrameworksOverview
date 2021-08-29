package app.suhocki.diframeworksoverview.presentation.login

import org.kodein.di.*

fun loginModule() = DI.Module(name = "login") {
    bind { scoped(LoginScope).singleton { LoginViewModel(instance(), instance(), instance()) } }
}
