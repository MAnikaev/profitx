package com.itis.core.network.request

import kotlinx.serialization.Serializable

@Serializable
data class ExpenseRequest(
    val name: String,
    val value: Int,
    val categoryName: String
)