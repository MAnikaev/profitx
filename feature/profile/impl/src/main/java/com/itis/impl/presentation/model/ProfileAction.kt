package com.itis.impl.presentation.model

sealed interface ProfileAction {
    data object NavigateToAuthorization : ProfileAction
    data object ShowHistoryClearedSnackBar : ProfileAction
}