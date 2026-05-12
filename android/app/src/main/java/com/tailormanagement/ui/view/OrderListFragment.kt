package com.tailormanagement.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tailormanagement.databinding.FragmentOrderListBinding
import com.tailormanagement.ui.viewmodel.OrderViewModel

class OrderListFragment : Fragment() {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderViewModel by viewModels()
    private lateinit var adapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OrderAdapter(emptyList())
        binding.rvOrders.adapter = adapter

        viewModel.orders.observe(viewLifecycleOwner) { orders ->
            adapter.updateData(orders)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchOrders()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
