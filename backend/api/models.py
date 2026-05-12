from django.db import models
from django.contrib.auth.models import AbstractUser

class User(AbstractUser):
    ROLE_CHOICES = (
        ('admin', 'Admin'),
        ('employee', 'Employee'),
    )
    role = models.CharField(max_length=10, choices=ROLE_CHOICES, default='employee')

class Employee(models.Model):
    SALARY_TYPE_CHOICES = (
        ('piece', 'Piece-based'),
        ('monthly', 'Monthly'),
    )
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='employee_profile')
    mobile = models.CharField(max_length=15)
    address = models.TextField()
    join_date = models.DateField(auto_now_add=True)
    salary_type = models.CharField(max_length=10, choices=SALARY_TYPE_CHOICES, default='piece')
    status = models.BooleanField(default=True)

    def __str__(self):
        return self.user.get_full_name() or self.user.username

class Customer(models.Model):
    name = models.CharField(max_length=255)
    mobile = models.CharField(max_length=15)
    address = models.TextField(blank=True, null=True)
    measurements = models.JSONField(default=dict)
    created_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.name

class Order(models.Model):
    CLOTH_TYPE_CHOICES = (
        ('shirt', 'Shirt'),
        ('pant', 'Pant'),
        ('blouse', 'Blouse'),
        ('kurta', 'Kurta'),
        ('suit', 'Suit'),
        ('custom', 'Custom'),
    )
    STATUS_CHOICES = (
        ('pending', 'Pending'),
        ('assigned', 'Assigned'),
        ('in_progress', 'In Progress'),
        ('completed', 'Completed'),
        ('delivered', 'Delivered'),
    )
    customer = models.ForeignKey(Customer, on_delete=models.CASCADE, related_name='orders')
    cloth_type = models.CharField(max_length=20, choices=CLOTH_TYPE_CHOICES)
    quantity = models.PositiveIntegerField(default=1)
    description = models.TextField(blank=True, null=True)
    image = models.ImageField(upload_to='order_images/', blank=True, null=True)
    delivery_date = models.DateField()
    status = models.CharField(max_length=20, choices=STATUS_CHOICES, default='pending')
    total_amount = models.DecimalField(max_digits=10, decimal_places=2)
    advance_payment = models.DecimalField(max_digits=10, decimal_places=2, default=0)
    remaining_payment = models.DecimalField(max_digits=10, decimal_places=2)
    created_at = models.DateTimeField(auto_now_add=True)

    def save(self, *args, **kwargs):
        self.remaining_payment = self.total_amount - self.advance_payment
        super().save(*args, **kwargs)

    def __str__(self):
        return f"Order #{self.id} - {self.customer.name}"

class WorkAssignment(models.Model):
    order = models.ForeignKey(Order, on_delete=models.CASCADE, related_name='assignments')
    employee_1 = models.ForeignKey(Employee, on_delete=models.CASCADE, related_name='assignments_1')
    employee_2 = models.ForeignKey(Employee, on_delete=models.SET_NULL, null=True, blank=True, related_name='assignments_2')
    split_percentage_1 = models.FloatField(default=100.0) # For employee_1
    split_percentage_2 = models.FloatField(default=0.0) # For employee_2
    completed = models.BooleanField(default=False)
    completion_date = models.DateTimeField(null=True, blank=True)
    assigned_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f"Work Assignment for Order #{self.order.id}"

class Payment(models.Model):
    order = models.ForeignKey(Order, on_delete=models.CASCADE, related_name='payments')
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    payment_date = models.DateTimeField(auto_now_add=True)
    payment_method = models.CharField(max_length=50, default='cash')

class SalaryHistory(models.Model):
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE, related_name='salary_history')
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    month = models.IntegerField()
    year = models.IntegerField()
    paid_at = models.DateTimeField(auto_now_add=True)
    status = models.CharField(max_length=20, default='paid')
