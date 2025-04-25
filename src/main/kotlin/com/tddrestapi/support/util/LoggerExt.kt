package com.tddrestapi.support.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

inline fun <reified T> T.getLogger(): Logger {
    return LoggerFactory.getLogger(getClassForLogging(T::class))
}

fun getClassForLogging(kClass: KClass<*>): Class<*> {
    return if (kClass.isCompanion) {
        kClass.java.enclosingClass ?: kClass.javaObjectType
    } else {
        kClass.java
    }
}
