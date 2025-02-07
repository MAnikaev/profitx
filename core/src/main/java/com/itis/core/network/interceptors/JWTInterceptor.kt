package com.itis.core.network.interceptors

import android.content.SharedPreferences
import com.itis.core.Constants
import okhttp3.Interceptor
import okhttp3.Response

class JwtInterceptor(
    private val sharedPrefs : SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .header(
                Constants.AUTHORIZATION_HEADER_KEY, ("Bearer " + sharedPrefs.getString(
                Constants.JWT_PREFS_KEY,
                "no"
            )))

        return chain.proceed(requestBuilder.build())
    }
}