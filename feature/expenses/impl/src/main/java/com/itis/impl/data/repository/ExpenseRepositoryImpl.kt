package com.itis.impl.data.repository

import com.itis.api.model.Expense
import com.itis.api.model.Category
import com.itis.api.repository.ExpenseRepository
import com.itis.core.network.ProfitXApi
import com.itis.impl.data.mapper.ExpenseMapper

class ExpenseRepositoryImpl(
    private val api: ProfitXApi,
    private val mapper: ExpenseMapper,
) : ExpenseRepository {
    override suspend fun getAllExpenses(): List<Expense> {
        return api.getAllExpenses().map {
            mapper.mapExpenseResponseToModel(it!!)
        }.filter { i ->
            i.category.id >= 7
        }
    }

    override suspend fun createExpense(expense: Expense) {
        api.createIncome(
            mapper.mapExpenseToRequest(expense)
        )
    }
}