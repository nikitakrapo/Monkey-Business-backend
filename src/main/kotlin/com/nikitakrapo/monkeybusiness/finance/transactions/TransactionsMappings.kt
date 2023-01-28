package com.nikitakrapo.monkeybusiness.finance.transactions

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import finance.transactions.TransactionItem as TransactionModel
import kotlinx.datetime.Instant

internal fun mapToTransaction(
    uuid: String,
    id: String,
    amount: Long,
    currency: String,
    timestamp: Long,
    name: String,
): Transaction =
    Transaction(
        id = id,
        moneyAmount = MoneyAmount(amount = amount, currency = Currency.fromCode(currency)),
        timestamp = timestamp,
        name = name,
    )
