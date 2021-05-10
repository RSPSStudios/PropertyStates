package com.daemonheim.game.api.properties

import kotlinx.coroutines.CoroutineScope

interface ObservableStateProperty<T> : CoroutineScope {

    fun addChangeListener(listener: (T) -> Unit)
    fun removeChangeListener(listener: (T) -> Unit)

}