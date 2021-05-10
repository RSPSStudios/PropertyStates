package com.daemonheim.game.api.properties

import kotlin.reflect.KProperty

interface WritableStateProperty<T> : ReadOnlyStateProperty<T> {

    operator fun setValue(ref: Any?, property: KProperty<*>, value: T)

}