package com.itis.api.domain.usecase

import com.itis.api.domain.model.RegistrationModel


interface RegistrationUseCase{

    suspend operator fun invoke(model: RegistrationModel)
}