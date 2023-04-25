package com.monkeybusiness.finance.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BankCardOpeningRequest(
    @SerialName("iban")
    val iban: String,
)
