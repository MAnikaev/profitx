package com.itis.core.network.request

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val email: String,

    val name: String ="123",
    val password: String
)