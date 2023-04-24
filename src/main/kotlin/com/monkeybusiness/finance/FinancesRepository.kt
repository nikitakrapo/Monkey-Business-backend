package com.monkeybusiness.finance

import app.cash.sqldelight.db.SqlDriver
import com.monkeybusiness.FinancesDatabase
import com.monkeybusiness.finance.dto.BankAccount
import com.monkeybusiness.finance.dto.BankCard
import com.monkeybusiness.finance.models.Currency
import com.monkeybusiness.sqldelight.asListSuspend

class FinancesRepository(
    sqlDriver: SqlDriver
) {
    init {
        FinancesDatabase.Schema.create(sqlDriver)
    }

    private val financesDatabase = FinancesDatabase(sqlDriver)
    private val accountsQueries get() = financesDatabase.bankAccountsDatabaseQueries
    private val cardsQueries get() = financesDatabase.bankCardsDatabaseQueries

    fun createBankAccount(
        uid: String,
        currency: Currency,
    ) {

    }

    suspend fun getAllBankAccounts(
        uid: String
    ) = accountsQueries.selectAllAccounts(uid)
        .asListSuspend()
        .map { account ->
            val cards = cardsQueries.selectAllCardsByIban(account.iban)
                .asListSuspend()
                .map { card -> BankCard(pan = card.pan) }
            BankAccount(
                iban = account.iban,
                balance = account.balance,
                currencyCode = account.currencyCode,
                cards = cards,
                name = account.name,
            )
        }
}