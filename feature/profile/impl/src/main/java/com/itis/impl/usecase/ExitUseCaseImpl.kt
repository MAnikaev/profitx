package com.itis.impl.usecase

import com.itis.api.repository.ProfileRepository
import com.itis.api.usecase.ExitUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ExitUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val repository: ProfileRepository
) : ExitUseCase {
    override suspend fun invoke() {
        withContext(dispatcher) {
            repository.exit()
        }
    }
}