package com.itis.impl.presentation.ui

import androidx.lifecycle.viewModelScope
import com.itis.api.domain.usecase.RegistrationUseCase
import com.itis.core.base.BaseViewModel
import com.itis.impl.data.mapper.RegistrationMapper
import com.itis.impl.presentation.model.RegistrationAction
import com.itis.impl.presentation.model.RegistrationEvent
import com.itis.impl.presentation.model.RegistrationState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val registrationUseCase: RegistrationUseCase,
    private val mapper: RegistrationMapper
) : BaseViewModel<RegistrationState, RegistrationEvent, RegistrationAction>(
    initialState = RegistrationState.Initial
) {
    override fun obtainEvent(event: RegistrationEvent) {
        super.obtainEvent(event)
        when(event) {
            is RegistrationEvent.AuthorizationButtonClicked -> goToAuthorizationScreen()
            is RegistrationEvent.RegisterButtonClicked -> {
                register(event.email, event.password)
            }
        }
    }

    private fun register(email: String, password: String) {
        viewModelScope.runCatching {
            launch {
                registrationUseCase.invoke(
                    mapper.mapEmailAndPasswordToRegistrationModel(email, password)
                )
            }
        }.onFailure {
            sendAction(RegistrationAction.ShowNotCorrectDataSnackBar)
        }.onSuccess {
            sendAction(RegistrationAction.Register)
        }
    }

    private fun goToAuthorizationScreen() {
        sendAction(RegistrationAction.NavigateToAuthorization)
    }
}