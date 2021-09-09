package app.suhocki.diframeworksoverview.presentation.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import toothpick.ktp.KTP

class FragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =
        if (KTP.isScopeOpen(className.toClass())) {
            runCatching {
                KTP.openScope(className.toClass()).getInstance(Class.forName(className)) as Fragment
            }.recover {
                super.instantiate(classLoader, className)
            }.getOrThrow()
        } else {
            super.instantiate(classLoader, className)
        }

    private fun String.toClass() = Class.forName(this).kotlin
}