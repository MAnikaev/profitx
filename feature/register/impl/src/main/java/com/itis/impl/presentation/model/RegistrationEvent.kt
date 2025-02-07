package com.itis.impl.presentation.model

sealed interface RegistrationEvent {
    data class RegisterButtonClicked(val email: String, val password: String) : RegistrationEvent
    data object AuthorizationButtonClicked: RegistrationEvent
}