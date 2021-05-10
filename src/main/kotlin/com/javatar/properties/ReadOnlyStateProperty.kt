package com.javatar.properties

import kotlin.reflect.KProperty

interface ReadOnlyStateProperty<T> {

    operator fun getValue(ref: Any?, property: KProperty<*>) : T {
        return getState()
    }

    fun getState() : T

}