package app.suhocki.diframeworksoverview.presentation.login

import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun loginModule() = module {
    bind<LoginViewModel>().singleton()
}