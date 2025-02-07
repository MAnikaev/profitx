package com.itis.impl.data.repository

import com.itis.api.model.Income
import com.itis.api.repository.IncomeRepository
import com.itis.core.network.ProfitXApi
import com.itis.impl.data.mapper.IncomeMapper

class IncomeRepositoryImpl(
    private val api: ProfitXApi,
    private val mapper: IncomeMapper,
) : IncomeRepository {
    override suspend fun getAllIncome(): List<Income> {
        return api.getAllIncome().map {
            mapper.mapIncomeResponseToModel(it!!)
        }.filter { i ->
            i.category.id < 7
        }
    }

    override suspend fun createIncome(income: Income) {
        api.createIncome(
            mapper.mapIncomeToRequest(income)
        )
    }
}