package com.monkeybusiness.accounts.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BankCard(
    @SerialName("pan")
    val pan: String,
)
