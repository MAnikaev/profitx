package com.itis.api.usecase

import com.itis.api.model.Expense

interface GetAllExpensesUseCase {
    suspend operator fun invoke() : List<Expense>
}