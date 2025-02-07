package com.itis.impl.usecase

import com.itis.api.model.Expense
import com.itis.api.repository.ExpenseRepository
import com.itis.api.usecase.CreateExpenseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CreateExpenseUseCaseImpl(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val repository: ExpenseRepository
) : CreateExpenseUseCase {
    override suspend fun invoke(expense: Expense) {
        withContext(coroutineDispatcher) {
            repository.createExpense(expense)
        }
    }
}