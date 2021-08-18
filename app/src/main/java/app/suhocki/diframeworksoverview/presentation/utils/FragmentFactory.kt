package app.suhocki.diframeworksoverview.presentation.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import app.suhocki.diframeworksoverview.di.toScopeID
import org.koin.core.scope.get
import org.koin.java.KoinJavaComponent

class FragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val scopeId = className.toClass().toScopeID()
        val scope = KoinJavaComponent.getKoin().getScopeOrNull(scopeId)
        return if (scope != null) {
            runCatching {
                scope.get(Class.forName(className)) as Fragment
            }.recover {
                super.instantiate(classLoader, className)
            }.getOrThrow()
        } else {
            super.instantiate(classLoader, className)
        }
    }

    private fun String.toClass() = Class.forName(this).kotlin
}