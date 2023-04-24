package com.monkeybusiness.accounts

import app.cash.sqldelight.db.SqlDriver
import com.monkeybusiness.finance.models.Currency

class BankAccountsRepository(
    sqlDriver: SqlDriver
) {
    init {
        BankAccountsDatabase.Schema.create(sqlDriver)
    }

    private val bankAccountsDatabase = BankAccountsDatabase(sqlDriver)
    private val dbQueries get() = bankAccountsDatabase.bankAccountsDatabaseQueries

    fun createBankAccount(
        uid: String,
        currency: Currency,
    ) {

    }

    fun getAllBankAccounts(
        uid: String
    ) {

    }
}