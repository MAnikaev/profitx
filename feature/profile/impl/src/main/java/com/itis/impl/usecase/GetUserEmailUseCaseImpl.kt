package com.itis.impl.usecase

import com.itis.api.repository.ProfileRepository
import com.itis.api.usecase.GetUserEmailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetUserEmailUseCaseImpl(
    private val repository: ProfileRepository,
    private val dispatcher: CoroutineDispatcher
) : GetUserEmailUseCase {
    override suspend fun invoke(): String {
        return withContext(dispatcher) {
            repository.getUserEmail()
        }
    }
}