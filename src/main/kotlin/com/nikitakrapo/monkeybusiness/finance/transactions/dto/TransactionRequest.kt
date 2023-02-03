package com.nikitakrapo.monkeybusiness.finance.transactions.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TransactionRequest(
    @SerialName("transaction")
    val transaction: Transaction,
)
