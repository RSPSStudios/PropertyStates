package com.daemonheim.game.api.properties

class ArrayStateProperty<T>(initialValue: Array<T>) : BaseStateProperty<Array<T>>(initialValue), ObservableArrayStateProperty<T, Array<T>> {

    override val arrayChangeListeners = mutableListOf<(Array<T>, T, T) -> Unit>()

    operator fun set(index: Int, value: T) {
        val element = this.value[index]
        this.value[index] = value
        arrayChangeListeners.forEach {
            it(this.value, element, value)
        }
    }

    operator fun get(index: Int) : T = this.value[index]

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var index: Int = 0
            override fun hasNext(): Boolean {
                return index < value.size
            }

            override fun next(): T {
                return value[index++]
            }
        }
    }

}