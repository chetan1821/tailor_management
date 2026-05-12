package com.tailormanagement.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tailormanagement.data.model.Order
import com.tailormanagement.databinding.ItemOrderBinding

class OrderAdapter(private var orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.tvCustomerName.text = order.customer_name
        holder.binding.tvStatus.text = order.status
        holder.binding.tvClothType.text = "${order.cloth_type.capitalize()} - Qty: ${order.quantity}"
        holder.binding.tvDeliveryDate.text = "Delivery: ${order.delivery_date}"
        holder.binding.tvTotalAmount.text = "Total: ₹${order.total_amount}"
    }

    override fun getItemCount() = orders.size

    fun updateData(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }
}
