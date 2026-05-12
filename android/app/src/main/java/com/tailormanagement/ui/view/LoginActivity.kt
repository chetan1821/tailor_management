package com.tailormanagement.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tailormanagement.databinding.ActivityLoginBinding
import com.tailormanagement.ui.viewmodel.LoginViewModel
import com.tailormanagement.utils.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // Check if user is already logged in
        if (sessionManager.fetchAuthToken() != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.et_username.text.toString()
            val password = binding.et_password.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginResponse.observe(this) { response ->
            if (response != null) {
                sessionManager.saveAuthToken(response.access)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnLogin.isEnabled = !isLoading
        }
    }
}
