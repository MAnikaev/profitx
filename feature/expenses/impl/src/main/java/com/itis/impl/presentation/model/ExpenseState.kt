package com.itis.impl.presentation.model

import com.itis.api.model.Expense

sealed interface ExpenseState {
    data object Initial: ExpenseState
    data class DataLoaded(val incomes: List<Expense>): ExpenseState
}