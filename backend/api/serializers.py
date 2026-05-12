from rest_framework import serializers
from .models import User, Employee, Customer, Order, WorkAssignment, Payment, SalaryHistory

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username', 'email', 'first_name', 'last_name', 'role')

class EmployeeSerializer(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)
    user_id = serializers.PrimaryKeyRelatedField(queryset=User.objects.all(), source='user', write_only=True)
    
    class Meta:
        model = Employee
        fields = '__all__'

class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Customer
        fields = '__all__'

class OrderSerializer(serializers.ModelSerializer):
    customer_name = serializers.CharField(source='customer.name', read_only=True)
    
    class Meta:
        model = Order
        fields = '__all__'

class WorkAssignmentSerializer(serializers.ModelSerializer):
    employee_1_name = serializers.CharField(source='employee_1.user.get_full_name', read_only=True)
    employee_2_name = serializers.CharField(source='employee_2.user.get_full_name', read_only=True)
    
    class Meta:
        model = WorkAssignment
        fields = '__all__'

class PaymentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Payment
        fields = '__all__'

class SalaryHistorySerializer(serializers.ModelSerializer):
    employee_name = serializers.CharField(source='employee.user.get_full_name', read_only=True)
    
    class Meta:
        model = SalaryHistory
        fields = '__all__'
