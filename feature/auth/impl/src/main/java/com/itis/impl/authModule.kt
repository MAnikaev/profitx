package com.itis.impl

import com.itis.api.domain.repository.LoginRepository
import com.itis.api.domain.usecase.LoginUseCase
import com.itis.impl.data.mapper.AuthorizationMapper
import com.itis.impl.data.repository.LoginRepositoryImpl
import com.itis.impl.presentation.ui.AuthViewModel
import com.itis.impl.usecase.LoginUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<AuthViewModel> {
        AuthViewModel(
            loginUseCase = get()
        )
    }

    factory<CoroutineDispatcher> { Dispatchers.IO }

    factory<LoginRepository> { LoginRepositoryImpl(
        api = get(),
        prefs = get(),
        mapper = get()
    ) }

    factory<LoginUseCase> { LoginUseCaseImpl(
        repository = get(),
        dispatcher = get()
    ) }
    factory { AuthorizationMapper() }
}