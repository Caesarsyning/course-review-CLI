# UVA Course Review CLI - Java Application

### Comprehensive Course Evaluation System

Welcome to the **UVA Course Review CLI**, a command-line interface (CLI) built in Java, designed to allow students to review University of Virginia (UVA) courses, view reviews, and export data in JSON format. This software uses a three-tier architecture, connecting the front-end CLI to an SQLite database and offering features such as user login, review submission, and review retrieval.

## Features

- **User Authentication:** Students can log in or create a new account with a unique username and password.
- **Course Review Submission:** Submit detailed reviews for UVA courses, including ratings and text comments.
- **View Course Reviews:** Access all reviews for a specific course, along with an average rating out of 5.
- **Personal Review Log:** Users can view their submitted reviews.
- **JSON Export:** Export all course reviews to a JSON file for easy sharing and analysis.
- **Automatic Course Data Import:** Automatically download and populate the course database from the UVA Developer API if it's empty.

## How to Run

### Windows:
1. Build the project:
    ```bash
    ./gradlew build
    ```
2. Run the JAR file:
    ```bash
    java -jar build/libs/Course_Review.jar
    ```

### macOS:
1. Give execution permission to `gradlew`:
    ```bash
    chmod +x ./gradlew
    ```
2. Build the project:
    ```bash
    ./gradlew build
    ```
3. Run the JAR file:
    ```bash
    java -jar build/libs/Course_Review.jar
    ```

## Architecture

This project follows a **3-tier architecture**:

1. **Presentation Layer**: CLI for user interaction (System.out and System.in).
2. **Business Logic Layer**: Handles data processing, validation, and formatting.
3. **Data Layer**: Manages the SQLite database and interacts with the UVA Developer API.
