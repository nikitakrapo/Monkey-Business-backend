package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.serialization.Serializable

//TODO: move to shared BE & Mobile module
@Serializable
data class MoneyAmount(
    val amount: Long,
    val currency: Currency
)
