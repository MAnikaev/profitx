package com.itis.api.usecase

import com.itis.api.model.Income

interface CreateIncomeUseCase {

    suspend operator fun invoke(income: Income)
}