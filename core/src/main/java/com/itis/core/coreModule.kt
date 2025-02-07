package com.itis.core

import android.content.Context
import android.content.SharedPreferences
import com.itis.core.network.ProfitXApi
import com.itis.core.network.interceptors.JwtInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val coreModule = module {

    single<SharedPreferences> { androidContext().getSharedPreferences(Constants.PROFITX_PREFS_KEY, Context.MODE_PRIVATE) }

    single<Json> { Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    } }

    single<ProfitXApi> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ProfitXApi::class.java)
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<JwtInterceptor>())
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    factory { JwtInterceptor(get()) }
}