package com.itis.api.model

data class Income(
    val id: Int = -1,
    val value: Int,
    val name: String,
    val date: String = "",
    val category: Category
)
