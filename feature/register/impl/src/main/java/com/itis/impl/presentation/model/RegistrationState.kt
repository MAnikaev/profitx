package com.itis.impl.presentation.model

sealed interface RegistrationState {
    data object Initial : RegistrationState
}