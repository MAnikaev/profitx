package com.itis.impl.usecase

import com.itis.api.model.Transaction
import com.itis.api.repository.AnalysisRepository
import com.itis.api.usecase.GetAllTransactionsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetAllTransactionsUseCaseImpl(
    private val repository: AnalysisRepository,
    private val dispatcher: CoroutineDispatcher
) : GetAllTransactionsUseCase {
    override suspend fun invoke(): List<Transaction> {
        return withContext(dispatcher)  {
            repository.getAllTransactions()
        }
    }
}