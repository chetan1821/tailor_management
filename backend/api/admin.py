from django.contrib import admin
from .models import User, Employee, Customer, Order, WorkAssignment, Payment, SalaryHistory

@admin.register(User)
class UserAdmin(admin.ModelAdmin):
    list_display = ('username', 'email', 'role', 'is_staff')
    list_filter = ('role', 'is_staff')

@admin.register(Employee)
class EmployeeAdmin(admin.ModelAdmin):
    list_display = ('user', 'mobile', 'salary_type', 'status')
    list_filter = ('salary_type', 'status')

@admin.register(Customer)
class CustomerAdmin(admin.ModelAdmin):
    list_display = ('name', 'mobile', 'created_at')
    search_fields = ('name', 'mobile')

@admin.register(Order)
class OrderAdmin(admin.ModelAdmin):
    list_display = ('id', 'customer', 'cloth_type', 'status', 'delivery_date', 'total_amount')
    list_filter = ('status', 'cloth_type')
    search_fields = ('customer__name',)

@admin.register(WorkAssignment)
class WorkAssignmentAdmin(admin.ModelAdmin):
    list_display = ('order', 'employee_1', 'employee_2', 'completed')
    list_filter = ('completed',)

@admin.register(Payment)
class PaymentAdmin(admin.ModelAdmin):
    list_display = ('order', 'amount', 'payment_date', 'payment_method')

@admin.register(SalaryHistory)
class SalaryHistoryAdmin(admin.ModelAdmin):
    list_display = ('employee', 'amount', 'month', 'year', 'status')
    list_filter = ('month', 'year', 'status')
