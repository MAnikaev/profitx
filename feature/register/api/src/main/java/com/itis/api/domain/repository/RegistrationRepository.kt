package com.itis.api.domain.repository

import com.itis.api.domain.model.RegistrationModel

interface RegistrationRepository {
    suspend fun register(model: RegistrationModel)
}