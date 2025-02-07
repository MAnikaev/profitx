package com.itis.impl

import com.itis.api.repository.ProfileRepository
import com.itis.api.usecase.ClearHistoryUseCase
import com.itis.api.usecase.ExitUseCase
import com.itis.api.usecase.GetUserEmailUseCase
import com.itis.impl.presentation.ui.ProfileViewModel
import com.itis.impl.repository.ProfileRepositoryImpl
import com.itis.impl.usecase.ClearHistoryUseCaseImpl
import com.itis.impl.usecase.ExitUseCaseImpl
import com.itis.impl.usecase.GetUserEmailUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    factory<CoroutineDispatcher> { Dispatchers.IO }

    factory<ProfileRepository> { ProfileRepositoryImpl(
        api = get(),
        prefs = get()
    ) }

    factory<ClearHistoryUseCase> { ClearHistoryUseCaseImpl(
        dispatcher = get(),
        repository = get()
    ) }

    factory<ExitUseCase> { ExitUseCaseImpl(
        dispatcher = get(),
        repository = get()
    ) }
    
    factory<GetUserEmailUseCase> { GetUserEmailUseCaseImpl(
        repository = get(),
        dispatcher = get()
    ) }

    viewModel<ProfileViewModel> { ProfileViewModel(
        exitUseCase = get(),
        clearHistoryUseCase = get(),
        getUserEmailUseCase = get(),
    ) }
}