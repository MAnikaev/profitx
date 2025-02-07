package com.itis.impl.repository

import com.itis.api.model.Transaction
import com.itis.api.repository.AnalysisRepository
import com.itis.core.network.ProfitXApi
import com.itis.impl.mapper.AnalysisMapper

class AnalysisRepositoryImpl(
    private val api: ProfitXApi,
    private val mapper: AnalysisMapper
) : AnalysisRepository {
    override suspend fun getAllTransactions(): List<Transaction> {
        val transactions = api.getAllTransactions().map {
            mapper.mapTransactionResponseToModel(it)
        }

        val categories = api.getAllCategories()
        categories.forEach { c ->
            for (i in transactions.indices) {
                if (transactions[i].categoryId == c.id) {
                    transactions[i].categoryName = c.name
                    transactions[i].categoryColor = c.color
                }
            }
        }
        return transactions
    }
}