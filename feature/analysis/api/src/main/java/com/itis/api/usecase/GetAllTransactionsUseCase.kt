package com.itis.api.usecase

import com.itis.api.model.Transaction

interface GetAllTransactionsUseCase {
    suspend operator fun invoke() : List<Transaction>
}