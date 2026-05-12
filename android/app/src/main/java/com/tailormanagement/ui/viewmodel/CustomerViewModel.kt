package com.tailormanagement.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tailormanagement.data.api.RetrofitClient
import com.tailormanagement.data.model.Customer
import kotlinx.coroutines.launch

class CustomerViewModel : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>> = _customers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCustomers() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getCustomers()
                if (response.isSuccessful) {
                    _customers.postValue(response.body())
                } else {
                    _error.postValue("Failed to fetch customers")
                }
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
