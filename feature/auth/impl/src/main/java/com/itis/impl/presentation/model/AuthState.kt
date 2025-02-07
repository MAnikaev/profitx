package com.itis.impl.presentation.model

// Состояния экрана
sealed interface AuthState {
    data object Initial: AuthState
    data object DataNotFilled: AuthState
}