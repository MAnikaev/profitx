package com.itis.impl

import com.itis.api.repository.AnalysisRepository
import com.itis.api.usecase.GetAllTransactionsUseCase
import com.itis.impl.mapper.AnalysisMapper
import com.itis.impl.presentation.ui.AnalysisViewModel
import com.itis.impl.repository.AnalysisRepositoryImpl
import com.itis.impl.usecase.GetAllTransactionsUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val analysisModule = module {
    
    factory<CoroutineDispatcher> { Dispatchers.IO }
    
    factory { AnalysisMapper() }

    factory<AnalysisRepository> { AnalysisRepositoryImpl(
        api = get(),
        mapper = get()
    ) }
    
    factory<GetAllTransactionsUseCase> { GetAllTransactionsUseCaseImpl(
        repository = get(),
        dispatcher = get()
    ) }

    viewModel<AnalysisViewModel> { AnalysisViewModel(
        getAllTransactionsUseCase = get()
    ) }
    
}