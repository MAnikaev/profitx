package com.itis.api.repository

import com.itis.api.model.Income

interface IncomeRepository {
    suspend fun getAllIncome() : List<Income>

    suspend fun createIncome(income: Income)
}