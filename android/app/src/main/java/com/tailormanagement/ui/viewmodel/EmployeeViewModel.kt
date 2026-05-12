package com.tailormanagement.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tailormanagement.data.api.RetrofitClient
import com.tailormanagement.data.model.Employee
import kotlinx.coroutines.launch

class EmployeeViewModel : ViewModel() {

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>> = _employees

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchEmployees() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getEmployees()
                if (response.isSuccessful) {
                    _employees.postValue(response.body())
                } else {
                    _error.postValue("Failed to fetch employees")
                }
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
