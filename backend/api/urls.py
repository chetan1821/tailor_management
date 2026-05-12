from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import (
    UserViewSet, EmployeeViewSet, CustomerViewSet,
    OrderViewSet, WorkAssignmentViewSet, PaymentViewSet,
    SalaryHistoryViewSet
)

router = DefaultRouter()
router.register(r'users', UserViewSet)
router.register(r'employees', EmployeeViewSet)
router.register(r'customers', CustomerViewSet)
router.register(r'orders', OrderViewSet)
router.register(r'work-assignments', WorkAssignmentViewSet)
router.register(r'payments', PaymentViewSet)
router.register(r'salary-history', SalaryHistoryViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
