# Swag Labs Test Automation

This repository contains automated tests for the **Swag Labs** web application, written using **Selenide** for browser automation and **TestNG** for test execution.

## Project Overview

The goal of this project is to automate the testing of the Swag Labs web application to ensure core functionalities work as expected. The tests are built using the Selenide framework for efficient browser automation and TestNG for organizing and running tests.

## Technologies

- **Selenide** – for browser automation.
- **TestNG** – for organizing and running tests.
- **Java 20** – for writing the tests.

## Prerequisites

Before getting started, make sure you have the following tools installed on your machine:

- **Java 20** or newer.
- **Maven** (for dependency management and running tests).
- **Git** (for cloning the repository).
- **Google Chrome** (the matching ChromeDriver is downloaded automatically by WebDriverManager).

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/okozhushko/swag-labs-tests.git
   cd swag-labs-tests
   ```

## Running the tests

```bash
# all tests
mvn clean test

# a single class or method
mvn test -Dtest=TestCheckUserLogin
mvn test -Dtest=TestCheckUserLogin#testLogout

# headless mode (enabled automatically when the CI environment variable is set)
mvn test -Dselenide.headless=true
```

Test classes must be named `Test*` to be picked up by Surefire.
