package com.itis.impl.presentation.model

interface IncomeEvent {
    data object AddIncomeButtonClicked : IncomeEvent
    data class AddIncomeClicked(val name: String, val sum: String, val categoryName: String) : IncomeEvent
}