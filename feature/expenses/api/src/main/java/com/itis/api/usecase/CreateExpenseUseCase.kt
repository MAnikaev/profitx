package com.itis.api.usecase

import com.itis.api.model.Expense

interface CreateExpenseUseCase {
    suspend operator fun invoke(expense: Expense)
}