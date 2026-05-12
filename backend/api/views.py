from rest_framework import viewsets, permissions, status
from rest_framework.response import Response
from rest_framework.decorators import action
from django.db.models import Sum, Count
from .models import User, Employee, Customer, Order, WorkAssignment, Payment, SalaryHistory
from .serializers import (
    UserSerializer, EmployeeSerializer, CustomerSerializer,
    OrderSerializer, WorkAssignmentSerializer, PaymentSerializer,
    SalaryHistorySerializer
)

class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

class EmployeeViewSet(viewsets.ModelViewSet):
    queryset = Employee.objects.all()
    serializer_class = EmployeeSerializer

class CustomerViewSet(viewsets.ModelViewSet):
    queryset = Customer.objects.all()
    serializer_class = CustomerSerializer

class OrderViewSet(viewsets.ModelViewSet):
    queryset = Order.objects.all()
    serializer_class = OrderSerializer

    @action(detail=False, methods=['get'])
    def dashboard_stats(self, request):
        total_employees = Employee.objects.count()
        active_orders = Order.objects.filter(status__in=['pending', 'assigned', 'in_progress']).count()
        completed_orders = Order.objects.filter(status='completed').count()
        pending_orders = Order.objects.filter(status='pending').count()
        total_income = Order.objects.aggregate(Sum('total_amount'))['total_amount__sum'] or 0
        
        return Response({
            'total_employees': total_employees,
            'active_orders': active_orders,
            'completed_orders': completed_orders,
            'pending_orders': pending_orders,
            'total_income': total_income,
            'today_work_summary': "Summary logic here"
        })

class WorkAssignmentViewSet(viewsets.ModelViewSet):
    queryset = WorkAssignment.objects.all()
    serializer_class = WorkAssignmentSerializer

class PaymentViewSet(viewsets.ModelViewSet):
    queryset = Payment.objects.all()
    serializer_class = PaymentSerializer

class SalaryHistoryViewSet(viewsets.ModelViewSet):
    queryset = SalaryHistory.objects.all()
    serializer_class = SalaryHistorySerializer
