package com.daemonheim.game.api.properties

fun <T> BaseStateProperty<T>.asReadOnly() : ReadOnlyStateProperty<T> {
    return this
}

fun <T> BaseStateProperty<T>.asObservable() : ObservableStateProperty<T> {
    return this
}

fun <T> BaseStateProperty<T>.asWritable() : WritableStateProperty<T> {
    return this
}

fun <T> BaseStateProperty<T>.asReadOnlyObservable() : ReadOnlyObservableStateProperty<T> {
    return this
}

fun <T> BaseStateProperty<T>.binding(vararg deps: BaseStateProperty<*>, supplier: () -> T) {
    deps.forEach {
        it.addChangeListener {
            setState(supplier())
        }
    }
}