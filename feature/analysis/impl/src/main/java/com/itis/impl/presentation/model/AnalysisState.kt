package com.itis.impl.presentation.model

import com.itis.api.model.Transaction


sealed interface AnalysisState {
    data object Initial: AnalysisState
    data class DataLoaded(val transactions: List<Transaction>) : AnalysisState
}