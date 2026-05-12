package com.tailormanagement.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val role: String
)

data class AuthResponse(
    val access: String,
    val refresh: String
)

data class Employee(
    val id: Int,
    val user: User,
    val mobile: String,
    val address: String,
    val join_date: String,
    val salary_type: String,
    val status: Boolean
)

data class Customer(
    val id: Int,
    val name: String,
    val mobile: String,
    val address: String?,
    val measurements: Map<String, Any>
)

data class Order(
    val id: Int,
    val customer: Int,
    val customer_name: String,
    val cloth_type: String,
    val quantity: Int,
    val delivery_date: String,
    val status: String,
    val total_amount: Double,
    val advance_payment: Double,
    val remaining_payment: Double
)
