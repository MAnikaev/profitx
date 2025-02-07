package com.itis.impl.presentation.model

sealed interface ExpenseEvent {
    data object AddExpenseButtonClicked : ExpenseEvent
    data class AddExpenseClicked(val name: String, val sum: String, val categoryName: String) : ExpenseEvent
}