package com.itis.impl

import com.itis.api.repository.IncomeRepository
import com.itis.api.usecase.CreateIncomeUseCase
import com.itis.api.usecase.GetAllIncomeUseCase
import com.itis.impl.data.mapper.IncomeMapper
import com.itis.impl.data.repository.IncomeRepositoryImpl
import com.itis.impl.presentation.ui.IncomeViewModel
import com.itis.impl.usecase.CreateIncomeUseCaseImpl
import com.itis.impl.usecase.GetAllIncomeUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val incomeModule = module {

    factory<CoroutineDispatcher> { Dispatchers.IO }

    factory { IncomeMapper() }

    factory<IncomeRepository> { IncomeRepositoryImpl(
        api = get(),
        mapper = get()
    ) }

    factory<GetAllIncomeUseCase> { GetAllIncomeUseCaseImpl(
        repository = get(),
        dispatcher = get()
    ) }
    
    factory<CreateIncomeUseCase> { CreateIncomeUseCaseImpl(
        coroutineDispatcher = get(),
        repository = get()
    ) }

    viewModel<IncomeViewModel> {
        IncomeViewModel(
            getAllIncomeUseCase = get(),
            createIncomeUseCase = get(),
        )
    }
}