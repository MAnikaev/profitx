package com.itis.api.domain.usecase

import com.itis.api.model.LoginModel

interface LoginUseCase {
    suspend operator fun invoke(model: LoginModel) : Boolean
}