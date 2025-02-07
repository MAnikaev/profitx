package com.itis.impl.presentation.ui

import androidx.lifecycle.viewModelScope
import com.itis.api.model.Category
import com.itis.api.model.Expense
import com.itis.api.usecase.CreateExpenseUseCase
import com.itis.api.usecase.GetAllExpensesUseCase
import com.itis.core.base.BaseViewModel
import com.itis.impl.presentation.model.ExpenseAction
import com.itis.impl.presentation.model.ExpenseEvent
import com.itis.impl.presentation.model.ExpenseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val getAllIncomeUseCase: GetAllExpensesUseCase,
    private val createIncomeUseCase: CreateExpenseUseCase
) : BaseViewModel<ExpenseState, ExpenseEvent, ExpenseAction>(
    initialState = ExpenseState.Initial
) {

    init {
        viewModelScope.launch {
            delay(1000)
            val incomes = getAllIncomeUseCase.invoke()
            _uiState.emit(ExpenseState.DataLoaded(incomes))
        }
    }

    override fun obtainEvent(event: ExpenseEvent) {
        super.obtainEvent(event)
        when(event) {
            is ExpenseEvent.AddExpenseButtonClicked -> {
                sendAction(ExpenseAction.NavigateToAddExpenseScreen)
            }
            is ExpenseEvent.AddExpenseClicked -> {
                createIncome(event.name, event.sum, event.categoryName)
            }
        }
    }

    private fun createIncome(name: String, sum: String, category: String) {
        if (name.isEmpty() || sum.isEmpty() || category.isEmpty()) {
            sendAction(ExpenseAction.ShowDataNotFilledSnackBar)
            return
        }

        viewModelScope.launch {
            createIncomeUseCase.invoke(
                Expense(
                    value = sum.toInt(),
                    name = name,
                    category = Category(
                        name = category,
                    )
                )
            )
            sendAction(ExpenseAction.NavigateToAllExpensesScreen)
        }
    }

}