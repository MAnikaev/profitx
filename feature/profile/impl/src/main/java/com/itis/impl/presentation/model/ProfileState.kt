package com.itis.impl.presentation.model

sealed interface ProfileState {
    data object Initial: ProfileState
    data class DataLoaded(val email: String) : ProfileState
}