package com.itis.impl.presentation.ui

import androidx.lifecycle.viewModelScope
import com.itis.api.domain.usecase.LoginUseCase
import com.itis.api.model.LoginModel
import com.itis.core.base.BaseViewModel
import com.itis.impl.presentation.model.AuthAction
import com.itis.impl.presentation.model.AuthEvent
import com.itis.impl.presentation.model.AuthState
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<AuthState, AuthEvent, AuthAction>(
    initialState = AuthState.Initial
) {

    override fun obtainEvent(event: AuthEvent) {
        super.obtainEvent(event)
        when(event) {
            is AuthEvent.LoginButtonClicked -> {
                login(event.email, event.password)
            }
            is AuthEvent.RegistrationButtonClicked -> {
                goToRegistrationScreen()
            }

        }
    }

    private fun goToRegistrationScreen() {
        sendAction(AuthAction.NavigateToRegistration)
    }

    private fun login(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            sendAction(AuthAction.Login)

            viewModelScope.launch {
                val isAuthenticated = loginUseCase(
                    LoginModel(
                        email = email,
                        password = password
                    )
                )
                if (isAuthenticated) sendAction(AuthAction.Login) else sendAction(AuthAction.ShowDataNotCorrectSnackBar)
            }

        } else {
            sendAction(AuthAction.ShowFieldsNotFilledSnackBar)
        }
    }
}