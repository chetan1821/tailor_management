package com.tailormanagement.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tailormanagement.data.api.RetrofitClient
import com.tailormanagement.data.model.AuthResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginResponse = MutableLiveData<AuthResponse?>()
    val loginResponse: LiveData<AuthResponse?> = _loginResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(username: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val credentials = mapOf("username" to username, "password" to password)
                val response = RetrofitClient.instance.login(credentials)
                if (response.isSuccessful) {
                    _loginResponse.postValue(response.body())
                } else {
                    _error.postValue("Invalid credentials")
                }
            } catch (e: Exception) {
                _error.postValue("Connection failed: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
