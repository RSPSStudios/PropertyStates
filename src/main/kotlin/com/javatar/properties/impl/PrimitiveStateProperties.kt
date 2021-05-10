package com.javatar.properties.impl

import com.javatar.properties.BaseStateProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class StringStateProperty(initialValue: String = "", scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)) :
    BaseStateProperty<String>(
        initialValue,
        scope
    )

class IntStateProperty(initialValue: Int = 0, scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)) :
    BaseStateProperty<Int>(initialValue, scope)

class DoubleStateProperty(initialValue: Double = 0.0, scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)) :
    BaseStateProperty<Double>(
        initialValue,
        scope
    )

class LongStateProperty(initialValue: Long = 0L, scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)) :
    BaseStateProperty<Long>(initialValue, scope)

class UIntStateProperty(initialValue: UInt = 0u, scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)) :
    BaseStateProperty<UInt>(initialValue, scope)

class UByteStateProperty(initialValue: UByte = 0u, scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)) :
    BaseStateProperty<UByte>(initialValue, scope)

class UShortStateProperty(initialValue: UShort = 0u, scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)) :
    BaseStateProperty<UShort>(initialValue, scope)