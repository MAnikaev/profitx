package com.itis.impl.data.repository

import com.itis.api.domain.model.RegistrationModel
import com.itis.api.domain.repository.RegistrationRepository
import com.itis.core.network.ProfitXApi
import com.itis.impl.data.mapper.RegistrationMapper

class RegistrationRepositoryImpl(
    private val api: ProfitXApi,
    private val mapper: RegistrationMapper
) : RegistrationRepository {
    override suspend fun register(model: RegistrationModel) {
        api.register(
            mapper.mapRegistrationModelToRequest(model)
        )
    }
}