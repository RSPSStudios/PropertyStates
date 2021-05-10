package com.daemonheim.game.api.properties

import com.daemonheim.game.api.properties.BaseStateProperty

open class ListStateProperty<T>(initialValue: MutableList<T> = mutableListOf()) : BaseStateProperty<MutableList<T>>(initialValue), MutableList<T> {

    private val listChangeListeners = mutableListOf<(List<T>, List<T>, Boolean, Boolean) -> Unit>()

    override val size: Int
        get() = value.size

    override fun add(element: T): Boolean {
        val wasAdded = value.add(element)
        listChangeListeners.forEach {
            it(value, listOf(element), wasAdded, false)
        }
        return wasAdded
    }

    override fun remove(element: T): Boolean {
        val wasRemoved = value.remove(element)
        listChangeListeners.forEach {
            it(value, listOf(element), false, wasRemoved)
        }
        return wasRemoved
    }

    fun addListChangeListener(listener : (List<T>, List<T>, Boolean, Boolean) -> Unit) {
        listChangeListeners.add(listener)
    }

    fun removeListChangeListener(listener: (List<T>, List<T>, Boolean, Boolean) -> Unit) {
        listChangeListeners.remove(listener)
    }

    override fun contains(element: T): Boolean {
        return value.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return value.containsAll(elements)
    }

    override fun get(index: Int): T {
        return value[index]
    }

    override fun indexOf(element: T): Int {
        return value.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return value.isEmpty()
    }

    override fun iterator(): MutableIterator<T> {
        return value.iterator()
    }

    override fun lastIndexOf(element: T): Int {
        return value.lastIndexOf(element)
    }

    override fun add(index: Int, element: T) {
        value.add(index, element)
        listChangeListeners.forEach {
            it(value, listOf(element), true, false)
        }
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val wasAdded = value.addAll(index, elements)

        listChangeListeners.forEach {
            it(value, elements.toList(), wasAdded, false)
        }

        return wasAdded
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val wasAdded = value.addAll(elements)
        listChangeListeners.forEach {
            it(value, elements.toList(), wasAdded, false)
        }
        return wasAdded
    }

    override fun clear() {
        value.clear()
    }

    override fun listIterator(): MutableListIterator<T> {
        return value.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<T> {
        return value.listIterator(index)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        val wasRemoved = value.removeAll(elements)
        listChangeListeners.forEach {
            it(value, elements.toList(), false, wasRemoved)
        }
        return wasRemoved
    }

    override fun removeAt(index: Int): T {
        val element = value.removeAt(index)
        if(element != null) {
            listChangeListeners.forEach {
                it(value, listOf(element), false, true)
            }
        }
        return element
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return value.retainAll(elements)
    }

    override fun set(index: Int, element: T): T {
        val e = value.set(index, element)
        if(e != null) {
            listChangeListeners.forEach {
                it(value, listOf(e), false, false)
            }
        }
        return e
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        return value.subList(fromIndex, toIndex)
    }

}