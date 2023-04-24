package com.monkeybusiness.finance.transactions

import app.cash.sqldelight.db.SqlDriver
import com.monkeybusiness.transactions.TransactionsDatabase
import com.monkeybusiness.finance.db.asListSuspend
import com.monkeybusiness.finance.transactions.dto.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionsRepository(
    sqlDriver: SqlDriver,
) {

    init {
        TransactionsDatabase.Schema.create(sqlDriver)
    }

    private val transactionsDatabase = TransactionsDatabase(sqlDriver)
    private val dbQueries get() = transactionsDatabase.transactionsDatabaseQueries

    suspend fun insertTransactions(
        uuid: String,
        transaction: Transaction
    ) {
        withContext(Dispatchers.IO) {
            dbQueries.insertTransaction(
                uuid = uuid,
                id = transaction.id,
                amount = transaction.moneyAmount.amount,
                currency = transaction.moneyAmount.currency.code,
                timestamp = transaction.timestampMs,
                name = transaction.name,
            )
        }
    }

    suspend fun getAllTransactions(uuid: String): List<Transaction> {
        return dbQueries.selectAllTransactions(
            uuid = uuid,
            mapper = ::mapToTransaction,
        ).asListSuspend()
    }
}