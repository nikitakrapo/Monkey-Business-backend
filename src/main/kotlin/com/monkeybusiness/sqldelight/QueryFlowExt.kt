package com.monkeybusiness.sqldelight

import app.cash.sqldelight.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> Query<T>.asListSuspend(): List<T> {
    return asFlow().map(Query<T>::executeAsList).take(1).last()
}

fun <T : Any> Query<T>.asFlow(): Flow<Query<T>> = flow {
    val channel = Channel<Unit>(CONFLATED)
    channel.trySend(Unit)

    val listener = Query.Listener { channel.trySend(Unit) }

    addListener(listener)
    try {
        for (item in channel) {
            emit(this@asFlow)
        }
    } finally {
        removeListener(listener)
    }
}

fun <T : Any> Flow<Query<T>>.mapToOne(
    context: CoroutineContext = Dispatchers.Default
): Flow<T> = map {
    withContext(context) {
        it.executeAsOne()
    }
}

fun <T : Any> Flow<Query<T>>.mapToOneOrDefault(
    defaultValue: T,
    context: CoroutineContext = Dispatchers.Default
): Flow<T> = map {
    withContext(context) {
        it.executeAsOneOrNull() ?: defaultValue
    }
}

fun <T : Any> Flow<Query<T>>.mapToOneOrNull(
    context: CoroutineContext = Dispatchers.Default
): Flow<T?> = map {
    withContext(context) {
        it.executeAsOneOrNull()
    }
}

fun <T : Any> Flow<Query<T>>.mapToOneNotNull(
    context: CoroutineContext = Dispatchers.Default
): Flow<T> = mapNotNull {
    withContext(context) {
        it.executeAsOneOrNull()
    }
}

fun <T : Any> Flow<Query<T>>.mapToList(
    context: CoroutineContext = Dispatchers.Default
): Flow<List<T>> = map {
    withContext(context) {
        it.executeAsList()
    }
}
