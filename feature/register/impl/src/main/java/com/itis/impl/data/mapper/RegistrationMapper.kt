package com.itis.impl.data.mapper

import com.itis.api.domain.model.RegistrationModel
import com.itis.core.network.request.RegistrationRequest

class RegistrationMapper {

    fun mapEmailAndPasswordToRegistrationModel(email: String, password: String) : RegistrationModel =
        RegistrationModel(
            email = email,
            password = password
        )
    fun mapRegistrationModelToRequest(model: RegistrationModel) : RegistrationRequest =
        RegistrationRequest(
            email = model.email,
            password = model.password
        )
}