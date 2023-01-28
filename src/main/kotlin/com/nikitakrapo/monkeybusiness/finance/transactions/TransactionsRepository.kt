package com.nikitakrapo.monkeybusiness.finance.transactions

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.nikitakrapo.monkeybusiness.finance.db.asListSuspend
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import finance.transactions.TransactionsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionsRepository {

    private val driver = run {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        TransactionsDatabase.Schema.create(driver)
        driver
    }
    private val transactionsDatabase = TransactionsDatabase(driver)
    private val dbQueries = transactionsDatabase.transactionsDatabaseQueries

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
                timestamp = transaction.timestamp,
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