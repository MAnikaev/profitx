package com.itis.impl.usecase

import com.itis.api.domain.repository.LoginRepository
import com.itis.api.domain.usecase.LoginUseCase
import com.itis.api.model.LoginModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val repository: LoginRepository
): LoginUseCase {
    override suspend fun invoke(model: LoginModel) : Boolean {
        return withContext(dispatcher) {
             repository.login(model)
        }
    }
}