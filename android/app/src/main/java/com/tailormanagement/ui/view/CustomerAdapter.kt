package com.tailormanagement.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tailormanagement.data.model.Customer
import com.tailormanagement.databinding.ItemCustomerBinding

class CustomerAdapter(private var customers: List<Customer>) : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    class CustomerViewHolder(val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customers[position]
        holder.binding.tvCustomerName.text = customer.name
        holder.binding.tvCustomerMobile.text = customer.mobile
    }

    override fun getItemCount() = customers.size

    fun updateData(newCustomers: List<Customer>) {
        customers = newCustomers
        notifyDataSetChanged()
    }
}
