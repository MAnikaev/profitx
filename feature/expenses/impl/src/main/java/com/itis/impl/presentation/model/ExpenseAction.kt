package com.itis.impl.presentation.model

sealed interface ExpenseAction {
    data object NavigateToAddExpenseScreen : ExpenseAction
    data object NavigateToAllExpensesScreen : ExpenseAction
    data object ShowDataNotFilledSnackBar: ExpenseAction
}