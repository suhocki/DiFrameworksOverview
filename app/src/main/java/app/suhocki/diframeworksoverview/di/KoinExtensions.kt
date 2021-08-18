package app.suhocki.diframeworksoverview.di

import kotlin.reflect.KClass

fun KClass<*>.toScopeID() = requireNotNull(qualifiedName)