# P1 - Full Stack Application

## Introduction

Project P1 is a full stack application that involves building a web application using Spring Boot for the backend and Angular for the frontend. The project covers the following key aspects:

Building RESTful APIs with Spring Boot
Implementing CRUD operations for data management
Integrating Angular frontend with Spring Boot backend

## User Stories

- **As a user**, I want to be able to understand the rules of the game in a easy and a concise manner.
- **As a user**, I want to be able to browse all potential monsters to choose my team.
- **As a user**, I want to be able to make more specific queries when searching for monster fights..
- **As a user**, I want to be able my team of monsters to fit according to my preferences.
- **As a user**, I want to be able to choose from a list of premade monsters to get started on the game faster.
- **As a user**, I want to be able to choose which enemy team that I would fight.
- **As a user**, I want to be able to influence combat with items or other abilities.
- **As a user**, I want to be able to see a battle report to determine how well I did against my opponent so I can do better next time.

## MVP (Minimum Viable Product)

- User registration and login
- Browsing and searching for products
- Adding products to a shopping cart
- Modifying the shopping cart
- Secure payment process
- Order history
- Product rating and reviewing

## Stretch Stories 

- **As a user**, I want to be able to play the game on Munchy Mode.
- **As a user**, I want to be able to build my own potential enemy teams.
- **As a user**, I want to be randomly matched against opponents.
- **As a user**, I want to be register an account to persist my monster teams.

## Stretch Goals

- Implementing a recommendation system based on user's previous purchases
- Adding an admin role that can add, remove, or modify products
- Implementing promotional codes and discounts
- Adding a wish list feature

## Tech Stacks

- **Java**: The main programming language used for building the application.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.

## Requirements

- **Clean Codebase**: All code should be clean and well-documented. The repository should not include any unnecessary files or folders such as the `target/`, `.DS_Store`, etc. All files and directories should be appropriately named and organized.

- **Database Design**: The database should be designed following the principles of the 3rd Normal Form (3NF) to ensure data integrity and efficiency. An Entity Relationship Diagram (ERD) should be included in the documentation.

- **Secure**: All sensitive user data such as passwords must be securely hashed before storing it in the database. The application should not display any sensitive information in error messages.

- **Error Handling**: The application should handle potential errors gracefully and provide clear and helpful error messages to the users.

- **Testing**: The application should have a high test coverage. Unit tests and integration tests should be implemented using JUnit, Mockito, and PowerMock.

- **Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting progress.

- **Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should be well-commented to allow for easy understanding and maintenance.

- **Scalable**: The design of the application should be scalable, allowing for easy addition of new features or modifications in the future.

