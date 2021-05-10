package com.javatar.properties

import kotlin.reflect.KProperty

interface StateProperty<T> : ReadOnlyStateProperty<T> {

    operator fun setValue(ref: Any?, property: KProperty<*>, value: T) {
        setState(value)
    }

    fun setState(value: T)

}