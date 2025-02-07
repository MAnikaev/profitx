package com.itis.api.usecase

interface GetUserEmailUseCase {
    suspend operator fun invoke() : String
}