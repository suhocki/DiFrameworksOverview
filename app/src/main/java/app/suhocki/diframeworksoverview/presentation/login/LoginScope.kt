package app.suhocki.diframeworksoverview.presentation.login

import app.suhocki.diframeworksoverview.di.Kodein
import org.kodein.di.bindings.UnboundedScope
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.on

object LoginScope : UnboundedScope() {
    override fun close() {
        Kodein.instance.on(LoginScope)
            .direct.instance<LoginViewModel>()
            .clear()
        super.close()
    }
}
