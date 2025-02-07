package com.itis.impl

import com.itis.api.domain.repository.RegistrationRepository
import com.itis.api.domain.usecase.RegistrationUseCase
import com.itis.impl.data.mapper.RegistrationMapper
import com.itis.impl.data.repository.RegistrationRepositoryImpl
import com.itis.impl.presentation.ui.RegistrationViewModel
import com.itis.impl.usecase.RegistrationUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {

    factory<CoroutineDispatcher> { Dispatchers.IO }

    factory { RegistrationMapper() }

    factory<RegistrationRepository> { RegistrationRepositoryImpl(
        api = get(),
        mapper = get()
    ) }

    factory<RegistrationUseCase> { RegistrationUseCaseImpl(
        dispatcher = get(),
        repository = get()
    ) }

    viewModel<RegistrationViewModel> {
        RegistrationViewModel(
            dispatcher = get(),
            registrationUseCase = get(),
            mapper = get()
        )
    }
}