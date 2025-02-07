package com.itis.api.model

data class Transaction(
    val name: String,
    var value: Int,
    val categoryId: Int,
    var categoryName: String,
    var categoryColor: String,
    var date: String
)