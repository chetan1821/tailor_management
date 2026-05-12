package com.tailormanagement.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.tailormanagement.databinding.ActivityMainBinding
import com.tailormanagement.utils.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        setSupportActionBar(binding.toolbar)

        setupNavigation()
        loadFragment(DashboardFragment())
    }

    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragment_container.id, fragment)
            .commit()
    }

    private fun setupNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                com.tailormanagement.R.id.nav_dashboard -> DashboardFragment()
                com.tailormanagement.R.id.nav_orders -> OrderListFragment()
                com.tailormanagement.R.id.nav_customers -> CustomerListFragment()
                com.tailormanagement.R.id.nav_employees -> EmployeeListFragment()
                // Add other fragments here as implemented
                else -> null
            }
            fragment?.let {
                loadFragment(it)
                true
            } ?: false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.tailormanagement.R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == com.tailormanagement.R.id.action_logout) {
            sessionManager.clearSession()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
