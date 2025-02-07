package com.itis.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Написал для себя, чтобы не запутаться
// Event - Действия пользователя
// State - состояние экрана
// Action - одноразовые события (SnackBar и тп)
abstract class BaseViewModel<State, Event, Action>(
    initialState: State
) : ViewModel() {

    // Flow для состояния (на его изменения реагируем на UI, меняем в ViewModel)
    protected val _uiState = MutableStateFlow(initialState)

    // Flow для одноразовых событий (реагируем на UI, меняем здесь)
    protected val _actionsFlow = MutableSharedFlow<Action>()

    val uiState: StateFlow<State>
        get() = _uiState.asStateFlow()

    val actionsFlow: SharedFlow<Action>
        get() = _actionsFlow.asSharedFlow()

    // Обрабатываем действия пользователя
    open fun obtainEvent(event: Event) {}

    open fun sendAction(action: Action) {
        viewModelScope.launch {
            _actionsFlow.emit(action)
        }
    }
}