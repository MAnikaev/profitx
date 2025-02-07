package com.itis.impl.usecase

import com.itis.api.model.Income
import com.itis.api.repository.IncomeRepository
import com.itis.api.usecase.CreateIncomeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CreateIncomeUseCaseImpl(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val repository: IncomeRepository
) : CreateIncomeUseCase {
    override suspend fun invoke(income: Income) {
        withContext(coroutineDispatcher) {
            repository.createIncome(income)
        }
    }
}