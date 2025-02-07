package com.itis.impl.presentation.model

// Реакция на действия пользователя
sealed interface AuthAction {
    data object NavigateToRegistration : AuthAction
    data object Login: AuthAction
    data object ShowFieldsNotFilledSnackBar: AuthAction
    data object ShowDataNotCorrectSnackBar: AuthAction
}