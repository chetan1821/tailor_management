package com.tailormanagement.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tailormanagement.databinding.FragmentEmployeeListBinding
import com.tailormanagement.ui.viewmodel.EmployeeViewModel

class EmployeeListFragment : Fragment() {

    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EmployeeViewModel by viewModels()
    private lateinit var adapter: EmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EmployeeAdapter(emptyList())
        binding.rvEmployees.adapter = adapter

        viewModel.employees.observe(viewLifecycleOwner) { employees ->
            adapter.updateData(employees)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchEmployees()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
