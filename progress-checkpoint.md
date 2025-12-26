# FoodBox Backend - Progress Checkpoint

## Completed: Group 1 - User & Customer

### ✅ What Works:
- User entity with enums (UserRole, UserStatus)
- Customer entity with foreign key to User
- Repositories (UserRepository, CustomerRepository)
- Services with transaction management
- REST API endpoint: POST /api/customers/register
- GET endpoint: GET /api/customers/user/{userId}

### 🗄️ Database Tables:
- users (8 columns, 3 rows)
- customers (4 columns, 3 rows with FK to users)

### 🧪 Tested:
- Customer registration via Postman ✅
- Data persisted in MySQL ✅
- Foreign key relationship working ✅

### 📚 Learned:
- JPA entity mapping
- One-to-One relationships
- Spring Data JPA repositories
- Service layer with @Transactional
- REST controller with @PostMapping/@GetMapping
- Manual getters/setters (Lombok issues)

### Next: Group 2 - Restaurant & MenuItem entities