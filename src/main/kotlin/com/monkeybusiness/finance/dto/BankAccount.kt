package com.monkeybusiness.finance.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BankAccount(
    @SerialName("iban")
    val iban: String,
    @SerialName("balance")
    val balance: Long,
    @SerialName("currencyCode")
    val currencyCode: String,
    @SerialName("cards")
    val cards: List<BankCard>,
    @SerialName("name")
    val name: String,
)
