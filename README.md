# **Employee Management System with Role-Based Access (Spring MVC)**  
**Objective**  
Build a **Spring MVC web application** where:  
- Admins can manage employees (add, edit, delete, view list).
- Regular users can only view employee details.
- The system uses Spring Security for authentication and authorization.
- All employee data is stored in a MySQL database.
---
**Requirements**  
**1. Technologies**  
- Java 8+
- Spring MVC
- Spring Security
- Hibernate/JPA
- MySQL
- Maven
---
**2. Database Tables**  
**USER**   
<div align="center">
    <table style="border-collapse: collapse; width: 100%;">
    <thead>
        <tr>
        <th style="border: 1px solid #555; padding: 8px;text-align:center;">Column</th>
        <th style="border: 1px solid #555; padding: 8px;text-align:center;">Type</th>
        <th style="border: 1px solid #555; padding: 8px;text-align:center;">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">id</td>
        <td style="border: 1px solid #555; padding: 8px;">BIGINT (PK)</td>
        <td style="border: 1px solid #555; padding: 8px;">User ID</td>
        </tr>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">username</td>
        <td style="border: 1px solid #555; padding: 8px;">VARCHAR(50)</td>
        <td style="border: 1px solid #555; padding: 8px;">Login username</td>
        </tr>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">password</td>
        <td style="border: 1px solid #555; padding: 8px;">VARCHAR(255)</td>
        <td style="border: 1px solid #555; padding: 8px;">Encrypted password</td>
        </tr>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">role</td>
        <td style="border: 1px solid #555; padding: 8px;">VARCHAR(20)</td>
        <td style="border: 1px solid #555; padding: 8px;">e.g., ADMIN or USER</td>
        </tr>
    </tbody>
    </table>
</div>

**EMPLOYEE**

<div align="center">
    <table style="border-collapse: collapse; width: 100%;">
    <thead>
        <tr>
        <th style="border: 1px solid #555; padding: 8px;text-align:center;">Column</th>
        <th style="border: 1px solid #555; padding: 8px;text-align:center;">Type</th>
        <th style="border: 1px solid #555; padding: 8px;text-align:center;">Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">id</td>
        <td style="border: 1px solid #555; padding: 8px;">BIGINT (PK)</td>
        <td style="border: 1px solid #555; padding: 8px;">Employee ID</td>
        </tr>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">name</td>
        <td style="border: 1px solid #555; padding: 8px;">VARCHAR(100)</td>
        <td style="border: 1px solid #555; padding: 8px;">Employee full name</td>
        </tr>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">email</td>
        <td style="border: 1px solid #555; padding: 8px;">VARCHAR(100)</td>
        <td style="border: 1px solid #555; padding: 8px;">Email address</td>
        </tr>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">department</td>
        <td style="border: 1px solid #555; padding: 8px;">VARCHAR(50)</td>
        <td style="border: 1px solid #555; padding: 8px;">Department name</td>
        </tr>
        <tr>
        <td style="border: 1px solid #555; padding: 8px;">salary</td>
        <td style="border: 1px solid #555; padding: 8px;">DOUBLE</td>
        <td style="border: 1px solid #555; padding: 8px;">Monthly salary</td>
        </tr>
    </tbody>
    </table>
</div>

---  

**3. Functional Requirements**  
**Authentication**  
- Implement login and logout using Spring Security.
- Passwords must be encrypted using BCrypt.

**Authorization**  
- ADMIN can: add, edit, delete, and view all employees.
- USER can: only view the employee list and details.

**CRUD Operations**  
- List all employees (`/employees`)
- View employee details (`/employees/{id}`)
- Add new employee (`/employees/add`)
- Edit employee (`/employees/edit/{id}`)
- Delete employee (`/employees/delete/{id}`)

**🖥️ Views (JSP)**  
- `login.jsp` — login page
- `employee-list.jsp` — shows all employees
- `employee-form.jsp` — form for add/edit
- `employee-details.jsp` — shows individual employee data
- `access-denied.jsp` — shown when user tries to access unauthorized page  

---

**4. Spring Security Configuration**  
Use `WebSecurityConfigurerAdapter` (if using Spring 5) or the newer `SecurityFilterChain` bean (if using Spring Boot 3+).  
**Rules:**  
- `/login, /logout` → accessible to everyone
- `/employees/**` → accessible only after login
- Only `ADMIN` role can perform POST requests for add/edit/delete
---
**5. Controller Structure**  
- `LoginController` — handles login/logout
- `EmployeeController` — handles all CRUD operations  
---
**6. Bonus Challenges**  
- Add a search bar to filter employees by name or department.
- Add pagination for the employee list.
- Send an email notification to the admin when a new employee is added (using Spring Mail).  

--- 

**7. Expected output**  
When you run the app:  

- Login as `admin` → full control. (Example: {username: admin, password: admin123})
- Login as `user` → read-only access. (Example: {username: test, password: test})
- Unauthorized users → redirected to login page.
- All pages styled with basic Bootstrap for readability.


