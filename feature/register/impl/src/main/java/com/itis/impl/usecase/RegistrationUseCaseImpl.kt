package com.itis.impl.usecase

import com.itis.api.domain.model.RegistrationModel
import com.itis.api.domain.repository.RegistrationRepository
import com.itis.api.domain.usecase.RegistrationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RegistrationUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val repository: RegistrationRepository
) : RegistrationUseCase {
    override suspend fun invoke(model: RegistrationModel) {
        withContext(dispatcher) {
            repository.register(model)
        }
    }
}