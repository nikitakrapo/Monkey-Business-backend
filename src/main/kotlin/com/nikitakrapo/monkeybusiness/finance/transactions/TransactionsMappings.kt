package com.nikitakrapo.monkeybusiness.finance.transactions

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.transactions.dto.Transaction

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
        timestampMs = timestamp,
        name = name,
    )
