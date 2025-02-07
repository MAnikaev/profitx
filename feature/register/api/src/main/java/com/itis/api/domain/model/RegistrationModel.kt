package com.itis.api.domain.model

data class RegistrationModel(
    val email: String,
    val name: String = "123",
    val password: String
)
