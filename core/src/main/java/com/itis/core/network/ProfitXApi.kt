package com.itis.core.network


import com.itis.core.network.request.ExpenseRequest
import com.itis.core.network.request.IncomeRequest
import com.itis.core.network.request.LoginRequest
import com.itis.core.network.request.RegistrationRequest
import com.itis.core.network.response.CategoryResponse
import com.itis.core.network.response.ExpenseResponse
import com.itis.core.network.response.IncomeResponse
import com.itis.core.network.response.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST


interface ProfitXApi {

    // Authorization

    @POST("signin")
    suspend fun login(@Body loginData: LoginRequest) : String?

    // Registration

    @POST("signup/user")
    suspend fun register(@Body registrationData: RegistrationRequest)

    // Income
    @GET("income/all")
    suspend fun getAllIncome() : List<IncomeResponse?>

    @POST("income/add")
    suspend fun createIncome(@Body incomeRequest: IncomeRequest)

    // Expense

    @GET("expense/all")
    suspend fun getAllExpenses() : List<ExpenseResponse?>

    @POST("expense/add")
    suspend fun createIncome(@Body expenseRequest: ExpenseRequest)

    // Transactions

    @GET("user/transactions")
    suspend fun getAllTransactions() : List<TransactionResponse>

    @DELETE("user/transactions")
    suspend fun clearHistory()

    // Categories

    @GET("category/all")
    suspend fun getAllCategories() : List<CategoryResponse>
}