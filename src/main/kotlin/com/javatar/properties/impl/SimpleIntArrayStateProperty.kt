package com.daemonheim.game.api.properties.impl

import com.daemonheim.game.api.properties.BaseStateProperty
import com.daemonheim.game.api.properties.ObservableArrayStateProperty

class SimpleIntArrayStateProperty(initialValue: IntArray) : BaseStateProperty<IntArray>(initialValue), ObservableArrayStateProperty<Int, IntArray> {
    override val arrayChangeListeners: MutableList<(IntArray, Int, Int) -> Unit> = mutableListOf()
    override fun iterator(): Iterator<Int> {
        return object : IntIterator() {
            private var index = 0
            override fun hasNext(): Boolean {
                return index < value.size
            }

            override fun nextInt(): Int {
                return value[index++]
            }
        }
    }
}