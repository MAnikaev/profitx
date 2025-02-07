package com.itis.api.domain.repository

import com.itis.api.model.LoginModel

interface LoginRepository {
    suspend fun login(model: LoginModel) : Boolean
}