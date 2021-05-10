package com.daemonheim.game.api.properties

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KProperty

abstract class BaseStateProperty<T>(
    initialValue: T,
    override val coroutineContext: CoroutineContext = Dispatchers.Unconfined
) : ReadOnlyObservableStateProperty<T>, WritableStateProperty<T> {

    private val _state = MutableStateFlow(initialValue)
    private val listenerJobs = mutableMapOf<(T) -> Unit, Job>()
    private var boundJob: Job? = null

    val state = _state.asStateFlow()

    var value: T
        get() = state.value
        set(value) {
            if(!isBound()) {
                _state.value = value
            } else {
                System.err.println("Can't change bound property, no-op action.")
            }
        }

    override operator fun getValue(ref: Any?, property: KProperty<*>): T {
        return state.value
    }

    override operator fun setValue(ref: Any?, property: KProperty<*>, value: T) {
        _state.value = value
    }

    fun bind(stateProperty: BaseStateProperty<T>) {
        if (isBound()) {
            error("Property already bound")
        }
        boundJob = stateProperty.state.onEach {
            this._state.value = it
        }.launchIn(this)
    }

    fun bindBidirectionally(stateProperty: BaseStateProperty<T>) {
        stateProperty.bind(this)
        bind(stateProperty)
    }

    fun unbind() {
        boundJob?.cancel()
        boundJob = null
    }

    fun isBound() = boundJob != null

    override fun addChangeListener(listener: (T) -> Unit) {
        listenerJobs[listener] = state.onEach { listener(it) }.launchIn(this)
    }

    override fun removeChangeListener(listener: (T) -> Unit) {
        listenerJobs[listener]?.cancel()
    }

    internal fun setState(state: T) {
        _state.value = state
    }
}