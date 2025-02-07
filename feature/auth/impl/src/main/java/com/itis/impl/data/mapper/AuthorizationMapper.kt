package com.itis.impl.data.mapper

import com.itis.core.network.request.LoginRequest
import com.itis.api.model.LoginModel

class AuthorizationMapper {

    fun mapLoginModelToLoginRequest(model: LoginModel) : LoginRequest =
        LoginRequest(
            email = model.email,
            password = model.password
        )
}