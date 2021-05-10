package com.daemonheim.game.api.properties

import kotlin.reflect.KProperty

interface ReadOnlyStateProperty<T> {

    operator fun getValue(ref: Any?, property: KProperty<*>) : T

}