package com.daemonheim.game.api.properties

interface ObservableArrayStateProperty<T, A> : Iterable<T> {
    val arrayChangeListeners: MutableList<(A, T, T) -> Unit>

    fun addArrayChangeListener(listener: (A, T, T) -> Unit) {
        arrayChangeListeners.add(listener)
    }

    fun removeArrayChangeListener(listener: (A, T, T) -> Unit) {
        arrayChangeListeners.remove(listener)
    }
}