package com.monkeybusiness.finance.transactions

import com.monkeybusiness.finance.models.Currency
import com.monkeybusiness.finance.models.MoneyAmount
import com.monkeybusiness.finance.transactions.dto.Transaction

internal fun mapToTransaction(
    uuid: String,
    id: String,
    amount: Double,
    currency: String,
    timestamp: Long,
    name: String,
): Transaction =
    Transaction(
        id = id,
        moneyAmount = MoneyAmount(amount = amount, currency = Currency.fromCode(currency)),
        timestampMs = timestamp,
        name = name,
    )
