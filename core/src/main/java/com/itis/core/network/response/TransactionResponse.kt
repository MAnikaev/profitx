package com.itis.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    val name: String,
    val value: Int,
    val date: String,
    val categoryId: Int
)
