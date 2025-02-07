package com.itis.impl.presentation.model

// Действия пользователя
sealed interface AuthEvent {
    data object RegistrationButtonClicked: AuthEvent
    data class LoginButtonClicked(val email: String, val password: String): AuthEvent

}