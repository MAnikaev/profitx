package com.itis.impl.presentation.model

sealed interface RegistrationAction {

    data object NavigateToAuthorization : RegistrationAction
    data object Register : RegistrationAction
    data object ShowNotCorrectDataSnackBar : RegistrationAction

}