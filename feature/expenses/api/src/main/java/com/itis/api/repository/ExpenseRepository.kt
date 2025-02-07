package com.itis.api.repository

import com.itis.api.model.Expense

interface ExpenseRepository {
    suspend fun getAllExpenses() : List<Expense>

    suspend fun createExpense(expense: Expense)
}