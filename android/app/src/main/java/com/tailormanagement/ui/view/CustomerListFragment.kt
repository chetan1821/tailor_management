package com.tailormanagement.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tailormanagement.databinding.FragmentCustomerListBinding
import com.tailormanagement.ui.viewmodel.CustomerViewModel

class CustomerListFragment : Fragment() {

    private var _binding: FragmentCustomerListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CustomerViewModel by viewModels()
    private lateinit var adapter: CustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CustomerAdapter(emptyList())
        binding.rvCustomers.adapter = adapter

        viewModel.customers.observe(viewLifecycleOwner) { customers ->
            adapter.updateData(customers)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchCustomers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
