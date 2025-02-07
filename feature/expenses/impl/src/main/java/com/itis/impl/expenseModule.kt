package com.itis.impl

import com.itis.api.repository.ExpenseRepository
import com.itis.api.usecase.CreateExpenseUseCase
import com.itis.api.usecase.GetAllExpensesUseCase
import com.itis.impl.data.mapper.ExpenseMapper
import com.itis.impl.data.repository.ExpenseRepositoryImpl
import com.itis.impl.presentation.ui.ExpenseViewModel
import com.itis.impl.usecase.CreateExpenseUseCaseImpl
import com.itis.impl.usecase.GetAllExpensesUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val expenseModule = module {

    factory<CoroutineDispatcher> { Dispatchers.IO }

    factory { ExpenseMapper() }

    factory<ExpenseRepository> { ExpenseRepositoryImpl(
        api = get(),
        mapper = get()
    ) }

    factory<GetAllExpensesUseCase> { GetAllExpensesUseCaseImpl(
        repository = get(),
        dispatcher = get()
    ) }

    factory<CreateExpenseUseCase> { CreateExpenseUseCaseImpl(
        coroutineDispatcher = get(),
        repository = get()
    ) }

    viewModel<ExpenseViewModel> {
        ExpenseViewModel(
            getAllIncomeUseCase = get(),
            createIncomeUseCase = get(),
        )
    }
}