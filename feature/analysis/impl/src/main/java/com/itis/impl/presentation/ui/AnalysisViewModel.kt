package com.itis.impl.presentation.ui

import androidx.lifecycle.viewModelScope
import com.itis.api.usecase.GetAllTransactionsUseCase
import com.itis.core.base.BaseViewModel
import com.itis.impl.presentation.model.AnalysisEvent
import com.itis.impl.presentation.model.AnalysisState
import kotlinx.coroutines.launch

class AnalysisViewModel(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
) : BaseViewModel<AnalysisState, AnalysisEvent, AnalysisState>(
    initialState = AnalysisState.Initial
) {

    init {
        viewModelScope.launch {
            _uiState.emit(
                AnalysisState.DataLoaded(getAllTransactionsUseCase.invoke())
            )
        }
    }

}