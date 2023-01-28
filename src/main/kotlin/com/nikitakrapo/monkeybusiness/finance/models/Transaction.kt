package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.serialization.Serializable

//TODO: move to shared BE & Mobile module
@Serializable
data class Transaction(
    val id: String,
    val moneyAmount: MoneyAmount,
    val timestamp: Long,
    val name: String
)
