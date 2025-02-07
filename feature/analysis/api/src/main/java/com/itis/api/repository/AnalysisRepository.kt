package com.itis.api.repository

import com.itis.api.model.Transaction

interface AnalysisRepository {
    suspend fun getAllTransactions() : List<Transaction>
}