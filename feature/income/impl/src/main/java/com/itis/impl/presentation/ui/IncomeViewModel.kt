package com.itis.impl.presentation.ui

import androidx.lifecycle.viewModelScope
import com.itis.api.model.Category
import com.itis.api.model.Income
import com.itis.api.usecase.CreateIncomeUseCase
import com.itis.api.usecase.GetAllIncomeUseCase
import com.itis.core.base.BaseViewModel
import com.itis.impl.presentation.model.IncomeAction
import com.itis.impl.presentation.model.IncomeEvent
import com.itis.impl.presentation.model.IncomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IncomeViewModel(
    private val getAllIncomeUseCase: GetAllIncomeUseCase,
    private val createIncomeUseCase: CreateIncomeUseCase
) : BaseViewModel<IncomeState, IncomeEvent, IncomeAction>(
    initialState = IncomeState.Initial
) {

    init {
        viewModelScope.launch {
            delay(1000)
            val incomes = getAllIncomeUseCase.invoke()
            _uiState.emit(IncomeState.DataLoaded(incomes))
        }
    }

    override fun obtainEvent(event: IncomeEvent) {
        super.obtainEvent(event)
        when(event) {
            is IncomeEvent.AddIncomeButtonClicked -> {
                sendAction(IncomeAction.NavigateToAddIncomeScreen)
            }
            is IncomeEvent.AddIncomeClicked -> {
                createIncome(event.name, event.sum, event.categoryName)
            }
        }
    }

    private fun createIncome(name: String, sum: String, category: String) {
        if (name.isEmpty() || sum.isEmpty() || category.isEmpty()) {
            sendAction(IncomeAction.ShowDataNotFilledSnackBar)
            return
        }

        viewModelScope.launch {
            createIncomeUseCase.invoke(
                Income(
                    value = sum.toInt(),
                    name = name,
                    category = Category(
                        name = category,
                    )
                )
            )
            sendAction(IncomeAction.NavigateToAllIncomesScreen)
        }
    }

}