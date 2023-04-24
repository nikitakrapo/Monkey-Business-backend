package com.monkeybusiness.finance.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BankAccountOpeningRequest(
    @SerialName("currencyCode")
    val currencyCode: String,
)