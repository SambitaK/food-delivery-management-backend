# Food Delivery Management System
A complete backend system for food delivery management built with Spring Boot, featuring JWT authentication and role-based access control.

## Overview
 
This project is a backend system for food delivery management. It supports three users — customers, restaurants, and drivers. Customers place orders, restaurants prepare them, drivers deliver them, and the system manages everything automatically including assigning drivers, handling payments, and tracking orders in real time.

## Features

### Customer Features
-  Secure registration and JWT-based authentication
-  Search restaurants by zip code
-  Browse menus and place orders with multiple items
-  Payment processing

### Restaurant Features
-  Menu management (add, update)
-  View and manage incoming orders
-  Accept/reject orders with automatic refunds
- ️ Set estimated pickup times

### Driver Features
-  Driver registration and profile management
-  Availability status management
-  Automatic order assignment
-  Delivery status updates

##  Tech Stack

**Backend Framework:** Spring Boot 3.3.5  
**Language:** Java 21  
**Database:** MySQL 8.0  
**ORM:** Hibernate/JPA  
**Security:** Spring Security + JWT  
**Password Encryption:** BCrypt  
**Build Tool:** Maven  


##  API Endpoints

### Authentication
```http
POST   /api/auth/login                    # Login (returns JWT token)
```

### Customer Management
```http
POST   /api/customers/register            # Register customer (public)
GET    /api/customers/user/{userId}       # Get customer profile (auth required)
```

### Restaurant Management
```http
POST   /api/restaurants/register          # Register restaurant (public)
GET    /api/restaurants/search?zipCode={} # Search by location (public)
GET    /api/restaurants                   # List all active (public)
GET    /api/restaurants/{id}              # Get details (public)
```

### Menu Management
```http
POST   /api/menu-items                    # Add item (RESTAURANT role)
GET    /api/menu-items/restaurant/{id}    # View menu (public)
PATCH  /api/menu-items/{id}/availability  # Update availability (RESTAURANT)
```

### Order Management
```http
POST   /api/orders                        # Place order (CUSTOMER role)
GET    /api/orders/{id}                   # Get order details (auth required)
GET    /api/orders/customer/{customerId}  # Customer order history (auth)
GET    /api/orders/restaurant/{restaurantId} # Restaurant orders (auth)
PATCH  /api/orders/{id}/accept            # Accept order (RESTAURANT role)
PATCH  /api/orders/{id}/reject            # Reject order + refund (RESTAURANT)
```

### Driver Management
```http
POST   /api/drivers/register              # Register driver (public)
GET    /api/drivers/available             # List available drivers (auth)
PATCH  /api/drivers/{id}/availability     # Update availability (DRIVER)
PATCH  /api/drivers/{id}/location         # Update GPS location (DRIVER)
```

### Delivery Management
```http
GET    /api/deliveries/order/{orderId}    # Get delivery by order (auth)
GET    /api/deliveries/driver/{driverId}  # Driver's deliveries (DRIVER)
PATCH  /api/deliveries/{id}/status        # Update delivery status (DRIVER)
```

**Total:** 23 REST endpoints with role-based access control

##  Security

### Authentication & Authorization

**JWT-Based Authentication:**
- Stateless token authentication (no server-side sessions)
- 24-hour token expiration
- HMAC-SHA256 (HS256) signing algorithm
- Token structure: `Header.Payload.Signature`

**Password Security:**
- BCrypt hashing with automatic salt generation
- Cost factor: 10 (adaptive)
- Passwords never stored in plain text

**Role-Based Access Control (RBAC):**

| Role | Allowed Actions |
|------|-----------------|
| **CUSTOMER** | Place orders, view history, track deliveries |
| **RESTAURANT** | Accept/reject orders, manage menu items |
| **DRIVER** | Update delivery status, view assigned deliveries |

### Security Flow
```
1. User registers → Password hashed (BCrypt) → Saved to DB
2. User logs in → Credentials validated → JWT generated with role
3. User sends request → JWT in Authorization header
4. JwtAuthenticationFilter validates token → Extracts role
5. @PreAuthorize checks role → Access granted/denied
```

### Implementation Details

**Components:**
- `JwtUtil` - Token generation and validation
- `JwtAuthenticationFilter` - Request interception and token validation
- `SecurityConfig` - Security rules and public/protected endpoint configuration
- `@PreAuthorize` - Method-level authorization annotations

**Protected Endpoints:** All sensitive operations (orders, payments, deliveries)  
**Public Endpoints:** Registration, login, restaurant search, menu viewing

## Author

**Sambita Khuntia**  
📧 Email: somyasambita11@gmail.com  
