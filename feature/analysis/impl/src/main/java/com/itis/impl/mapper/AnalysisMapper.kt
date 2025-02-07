package com.itis.impl.mapper

import com.itis.api.model.Transaction
import com.itis.core.network.response.TransactionResponse

class AnalysisMapper {
    
    fun mapTransactionResponseToModel(response: TransactionResponse) : Transaction = 
        Transaction(
            categoryId = response.categoryId,
            name = response.name,
            value = response.value,
            categoryName = "",
            categoryColor = "",
            date = response.date
        )
    
}