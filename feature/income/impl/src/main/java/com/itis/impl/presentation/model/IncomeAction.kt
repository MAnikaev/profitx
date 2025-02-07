package com.itis.impl.presentation.model

interface IncomeAction {
    data object NavigateToAddIncomeScreen : IncomeAction
    data object NavigateToAllIncomesScreen : IncomeAction
    data object ShowDataNotFilledSnackBar: IncomeAction
}