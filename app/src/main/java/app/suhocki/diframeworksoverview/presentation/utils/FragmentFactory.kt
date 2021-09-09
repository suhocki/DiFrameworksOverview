package app.suhocki.diframeworksoverview.presentation.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import app.suhocki.diframeworksoverview.di.Kodein
import org.kodein.di.direct
import org.kodein.di.instance

class FragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return Kodein.instance.direct.instance(className)
    }
}