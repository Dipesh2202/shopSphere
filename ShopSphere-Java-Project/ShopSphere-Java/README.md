# ShopSphere — Java E-commerce Portfolio Project

A responsive shopping website built with **Java 17, Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, MySQL, Bootstrap 5 and custom CSS**.

## Features
- Responsive storefront with product cards and product detail pages
- Search by product name or category
- Add to cart, update quantities, remove items, live cart count
- Checkout form with basic server-side validation
- Persists orders in MySQL and shows an order confirmation page
- Demo product seed data on first launch
- Session-based cart

## Important demo limitations
This is a portfolio project, not a production Amazon clone. It does **not** take real payments, create customer accounts, send delivery notifications, or provide a full admin dashboard. Do not enter sensitive real customer data. A payment provider, authentication, CSRF/security hardening, stock locking, and order-management UI should be added before production use.

## Requirements
- JDK 17+
- Maven 3.8+
- MySQL 8+

## Run locally
1. Create a database (optional because `createDatabaseIfNotExist=true` is configured):
   ```sql
   CREATE DATABASE shopsphere;
   ```
2. Set MySQL credentials. Default username is `root` and default password is blank. For a different setup, set environment variables:
   - `DB_URL=jdbc:mysql://localhost:3306/shopsphere?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC`
   - `DB_USERNAME=your_mysql_username`
   - `DB_PASSWORD=your_mysql_password`
3. In this project folder, run:
   ```bash
   mvn spring-boot:run
   ```
4. Open `http://localhost:8080`.

To create a runnable jar:
```bash
mvn clean package
java -jar target/shopsphere-1.0.0.jar
```

## Publish it online
A shareable company-facing URL requires deploying the app. This project needs a Java-capable host and a managed MySQL database; a static website host alone will not run the Java backend. Add the host's database URL, username and password as environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`). Set `PORT` if the host provides one. Do not commit production credentials.

Suggested path:
1. Push this folder to a new GitHub repository.
2. Create a managed MySQL database with a hosting provider.
3. Deploy the Spring Boot app to a Java-capable application host (for example, Render or Railway, after checking their current Java/build support and pricing).
4. Configure the environment variables and wait for the build to succeed.
5. Test product search, add/remove/update cart, checkout, and order persistence on the deployed URL.
6. Put the final public URL and GitHub repository link on your resume.

## Resume project description
**ShopSphere — Java E-commerce Web Application**
- Built a responsive shopping platform using Java, Spring Boot, Spring MVC, Thymeleaf, Bootstrap and MySQL.
- Implemented product browsing, category/name search, session-based cart management and checkout workflow.
- Used Spring Data JPA/Hibernate to persist product and order data in MySQL.
- Designed responsive product, cart, checkout and order confirmation pages.

## Folder layout
- `src/main/java`: Spring Boot application, entities, repositories, controller, demo data
- `src/main/resources/templates`: Thymeleaf HTML pages
- `src/main/resources/static/css`: responsive styling
- `src/main/resources/application.properties`: database and server configuration
