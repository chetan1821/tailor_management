package com.tailormanagement.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tailormanagement.data.api.RetrofitClient
import com.tailormanagement.data.model.Order
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> = _orders

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchOrders() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getOrders()
                if (response.isSuccessful) {
                    _orders.postValue(response.body())
                } else {
                    _error.postValue("Failed to fetch orders")
                }
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
