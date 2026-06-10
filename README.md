# Library Management System

A Java Spring Boot-based web application for managing library books, authors, publishers, categories, and borrowing transactions.

## Features
- **Modern UI**: Dark-theme interface with custom CSS styles.
- **Book Catalog**: Add, update, view, and delete books, authors, categories, and publishers.
- **Book Issuing**: Manage student book loans and track active/returned books.
- **User Registration**: Register custom admin accounts to log in and manage the library.

## Local Setup

### Prerequisites
- JDK 17 or higher
- Apache Maven

### Instructions
1. Download or clone the source code to your machine.
2. Run compilation:
   ```bash
   mvn clean install
   ```
3. Start the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the web interface at: `http://localhost:9080`
5. Default login credentials:
   - **Username**: `admin@library.com`
   - **Password**: `Temp123`

