package com.itis.impl.presentation.model

sealed interface ProfileEvent {
    data object ClearHistoryButtonClicked : ProfileEvent
    data object ExitButtonClicked : ProfileEvent
}