package com.itis.impl.usecase

import com.itis.api.model.Expense
import com.itis.api.repository.ExpenseRepository
import com.itis.api.usecase.GetAllExpensesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetAllExpensesUseCaseImpl(
    private val repository: ExpenseRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetAllExpensesUseCase {
    override suspend fun invoke() : List<Expense> {
        return withContext(dispatcher) {
            repository.getAllExpenses()
        }
    }
}