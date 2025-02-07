package com.itis.impl.presentation.model

import com.itis.api.model.Income

sealed interface IncomeState {
    data object Initial: IncomeState
    data class DataLoaded(val incomes: List<Income>): IncomeState
}