package com.monkeybusiness.finance.transactions.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TransactionsResponse(
    @SerialName("transactionList")
    val transactionList: List<Transaction>,
)
