package com.tailormanagement.data.api

import com.tailormanagement.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("token/")
    suspend fun login(@Body credentials: Map<String, String>): Response<AuthResponse>

    @GET("orders/")
    suspend fun getOrders(): Response<List<Order>>

    @GET("orders/dashboard_stats/")
    suspend fun getDashboardStats(): Response<Map<String, Any>>

    @GET("employees/")
    suspend fun getEmployees(): Response<List<Employee>>

    @GET("customers/")
    suspend fun getCustomers(): Response<List<Customer>>

    @POST("orders/")
    suspend fun createOrder(@Body order: Order): Response<Order>
}
