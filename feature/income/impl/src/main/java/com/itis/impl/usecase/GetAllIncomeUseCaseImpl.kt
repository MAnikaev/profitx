package com.itis.impl.usecase

import com.itis.api.model.Income
import com.itis.api.repository.IncomeRepository
import com.itis.api.usecase.GetAllIncomeUseCase
import com.itis.impl.data.mapper.IncomeMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetAllIncomeUseCaseImpl(
    private val repository: IncomeRepository,
    private val dispatcher: CoroutineDispatcher,
) : GetAllIncomeUseCase {
    override suspend fun invoke() : List<Income> {
        return withContext(dispatcher) {
            repository.getAllIncome()
        }
    }
}