package com.itis.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class IncomeResponse(
    val id: Int,
    val value: Int,
    val name: String,
    val date: String,
    val category: CategoryResponse
)