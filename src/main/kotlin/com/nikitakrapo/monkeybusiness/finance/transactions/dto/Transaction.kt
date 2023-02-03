package com.nikitakrapo.monkeybusiness.finance.transactions.dto

import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO: move to shared BE & Mobile module
@Serializable
data class Transaction(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("moneyAmount")
    val moneyAmount: MoneyAmount,
    @SerialName("timestampMs")
    val timestampMs: Long
)
