package com.itis.api.repository

interface ProfileRepository {
    suspend fun exit()
    suspend fun clearHistory()
    suspend fun getUserEmail() : String
}