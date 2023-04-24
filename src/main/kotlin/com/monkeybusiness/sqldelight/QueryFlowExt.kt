package com.monkeybusiness.sqldelight

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

suspend fun <T : Any> Query<T>.asOneSuspend(): T {
    return asFlow().map(Query<T>::executeAsOne).take(1).last()
}

suspend fun <T : Any> Query<T>.asOneOrNullSuspend(): T? {
    return asFlow().map(Query<T>::executeAsOneOrNull).take(1).last()
}

suspend fun <T : Any> Query<T>.asListSuspend(): List<T> {
    return asFlow().map(Query<T>::executeAsList).take(1).last()
}
