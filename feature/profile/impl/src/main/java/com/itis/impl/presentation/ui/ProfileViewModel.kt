package com.itis.impl.presentation.ui

import androidx.lifecycle.viewModelScope
import com.itis.api.usecase.ClearHistoryUseCase
import com.itis.api.usecase.ExitUseCase
import com.itis.api.usecase.GetUserEmailUseCase
import com.itis.core.base.BaseViewModel
import com.itis.impl.presentation.model.ProfileAction
import com.itis.impl.presentation.model.ProfileEvent
import com.itis.impl.presentation.model.ProfileState
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val exitUseCase: ExitUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase,
    private val getUserEmailUseCase: GetUserEmailUseCase
) : BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
    initialState = ProfileState.Initial
) {

    init {
        viewModelScope.launch {
            _uiState.emit(ProfileState.DataLoaded(
                email = getUserEmailUseCase()
            ))
        }
    }

    override fun obtainEvent(event: ProfileEvent) {
        super.obtainEvent(event)
        when(event) {
            ProfileEvent.ClearHistoryButtonClicked -> clearHistory()
            ProfileEvent.ExitButtonClicked -> exit()
        }
    }

    private fun clearHistory() {
        viewModelScope.launch {
            clearHistoryUseCase()
            sendAction(ProfileAction.ShowHistoryClearedSnackBar)
        }
    }

    private fun exit() {
        viewModelScope.launch {
            exitUseCase()
            sendAction(ProfileAction.NavigateToAuthorization)
        }
    }

}