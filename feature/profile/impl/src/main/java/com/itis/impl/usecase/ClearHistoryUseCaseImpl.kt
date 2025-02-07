package com.itis.impl.usecase

import com.itis.api.repository.ProfileRepository
import com.itis.api.usecase.ClearHistoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ClearHistoryUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val repository: ProfileRepository
) : ClearHistoryUseCase {
    override suspend fun invoke() {
        withContext(dispatcher) {
            repository.clearHistory()
        }
    }
}