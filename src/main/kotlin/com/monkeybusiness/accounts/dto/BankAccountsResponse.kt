package com.monkeybusiness.accounts.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BankAccountsResponse(
    @SerialName("accounts")
    val accounts: List<BankAccount>,
)
