package com.itis.impl.repository

import android.content.SharedPreferences
import com.itis.api.repository.ProfileRepository
import com.itis.core.Constants
import com.itis.core.network.ProfitXApi

class ProfileRepositoryImpl(
    private val api: ProfitXApi,
    private val prefs: SharedPreferences
) : ProfileRepository{
    override suspend fun exit() {
        prefs.edit()
            .remove(Constants.JWT_PREFS_KEY)
            .remove(Constants.EMAIL_PREFS_KEY)
            .apply()
    }

    override suspend fun clearHistory() {
        api.clearHistory()
    }

    override suspend fun getUserEmail(): String {
        return prefs.getString(Constants.EMAIL_PREFS_KEY, "no")!!
    }
}