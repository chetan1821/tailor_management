package com.tailormanagement.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tailormanagement.data.model.Employee
import com.tailormanagement.databinding.ItemEmployeeBinding

class EmployeeAdapter(private var employees: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.binding.tvEmployeeName.text = employee.user.username
        holder.binding.tvSalaryType.text = "Salary: ${employee.salary_type.capitalize()}"
        holder.binding.chipStatus.text = if (employee.status) "Active" else "Inactive"
    }

    override fun getItemCount() = employees.size

    fun updateData(newEmployees: List<Employee>) {
        employees = newEmployees
        notifyDataSetChanged()
    }
}
