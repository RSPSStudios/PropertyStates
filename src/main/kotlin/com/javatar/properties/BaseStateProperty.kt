package com.javatar.properties

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseStateProperty<T>(initialValue: T, scope: CoroutineScope) : ObservableStateProperty<T>, CoroutineScope by scope {

    private val stateFlow = MutableStateFlow(initialValue)

    private val listeners = mutableMapOf<(ObservableStateProperty<T>, T) -> Unit, Job>()

    override fun getState(): T {
        return stateFlow.value
    }

    override fun setState(value: T) {
        stateFlow.value = value
    }

    override fun addListener(listener: (ObservableStateProperty<T>, T) -> Unit) {
        stateFlow.onEach {
            listener(this, it)
        }.launchIn(this)
    }

    override fun removeListener(listener: (ObservableStateProperty<T>, T) -> Unit) {
        listeners.remove(listener)?.cancel()
    }
}