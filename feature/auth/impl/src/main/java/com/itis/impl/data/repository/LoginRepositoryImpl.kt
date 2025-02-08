package com.itis.impl.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.Trace
import com.itis.api.domain.repository.LoginRepository
import com.itis.api.model.LoginModel
import com.itis.core.Constants
import com.itis.core.network.ProfitXApi
import com.itis.impl.data.mapper.AuthorizationMapper

class LoginRepositoryImpl(
    private val api: ProfitXApi,
    private val mapper: AuthorizationMapper,
    private val prefs: SharedPreferences
) : LoginRepository {
    override suspend fun login(model: LoginModel): Boolean {

        val trace: Trace = FirebasePerformance.getInstance().newTrace("login_trace")
        trace.start()
        val response = api.login(
            mapper.mapLoginModelToLoginRequest(model)
        )
        trace.stop()
        if (!response.isNullOrEmpty() && response != "Wrong password" && response != "Wrong email") {
            prefs.edit()
                .putString(Constants.JWT_PREFS_KEY, response)
                .putString(Constants.EMAIL_PREFS_KEY, model.email)
                .apply()

            return true
        } else {
            return false
        }
    }
}