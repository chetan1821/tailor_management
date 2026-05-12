package com.tailormanagement.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tailormanagement.data.api.RetrofitClient
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _stats = MutableLiveData<Map<String, Any>>()
    val stats: LiveData<Map<String, Any>> = _stats

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchStats() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getDashboardStats()
                if (response.isSuccessful) {
                    _stats.postValue(response.body())
                } else {
                    _error.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _error.postValue("Exception: ${e.message}")
            }
        }
    }
}
