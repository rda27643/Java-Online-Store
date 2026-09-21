# Online Store 🛒

A simple console-based online store built with Java.

This project was built to practice Java by putting different concepts together in one application — from managing products and stock to handling carts and orders.

### What it can do

* Add, remove and view products
* Manage product stock
* Add and manage cart items
* Create and manage orders
* Update order status
* Handle invalid input and business errors with custom exceptions

### Order Flow

```text
PENDING
   ├──> CONFIRMED
   │       ├──> COMPLETED
   │       └──> CANCELLED
   │
   └──> CANCELLED
```

### Structure

```text
model       →  application data
service     →  business logic
exception   →  custom exceptions
util        →  input & validation utilities
Main        →  console interface
```

### Built With

* Java
* OOP
* Collections
* Exception Handling
* Enums
* Encapsulation
* Service-based architecture

### Note

This is a console application and currently keeps its data in memory.
No database, authentication, payment system, or web interface is included.

Built as part of my journey toward backend development with Java.
