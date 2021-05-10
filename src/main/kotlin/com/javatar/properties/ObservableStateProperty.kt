package com.javatar.properties

interface ObservableStateProperty<T> : StateProperty<T> {

    fun addListener(listener: (ObservableStateProperty<T>, T) -> Unit)
    fun removeListener(listener: (ObservableStateProperty<T>, T) -> Unit)

}