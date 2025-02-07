package com.itis.api.usecase

import com.itis.api.model.Income

interface GetAllIncomeUseCase {
    suspend operator fun invoke() : List<Income>
}