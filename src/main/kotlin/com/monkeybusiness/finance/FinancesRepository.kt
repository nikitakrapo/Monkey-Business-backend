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
        accountsQueries.insertAccount(
            iban = createIban(),
            uid = uid,
            balance = 0,
            currencyCode = currency.code,
            name = "Bank account",
        )
    }

    // now just random uuid
    private fun createIban() = buildString {
        append("RU")
        val checksum = randomNumberString(4)
        append(checksum)
        val bban = randomNumberString(24)
        append(bban )
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

    fun createBankCard(
        iban: String,
    ) {
        cardsQueries.insertCard(
            pan = createPan(),
            iban = iban,
        )
    }

    // now just random pan
    private fun createPan() = buildString {
        val iin = "4321"
        append(iin)
        val rest = randomNumberString(12)
        append(rest)
    }

    private fun randomNumberString(length: Int): String {
        val allowedChars = ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}