# Scrum Retrospective Application

A RESTful web service using Java Spring Framework
## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started & Installation](#getting-started and installation)
- [Usage](#usage)
- [Tests](#tests)
- [Contributing](#contributing)
- [License](#license)

## Introduction

A RESTful web service that can be accessed via HTTP. The service allows users to capture and query retrospective (SCRUM ceremony) outcomes. A retrospective is a SCRUM ceremony that allows development teams to reflect on their software delivery during previous iteration (sprint). 

## Features

List the key features of your Spring Boot application:

- Api to create a retrospective
- Api to get all retrospectives
- Api to search for retrospective by entry date
- Api to add a feedback to a retrospective
- Api to update a feedback

## Prerequisites

1. You will require Java 8 or higher.
2. You will also require Git to be installed

## Getting Started & Installation
```bash
# Clone the repository
git clone https://github.com/weezyjagbabe/sis-retrospect.git

# Navigate to the project directory
cd sis-retrospect folder

# Build the project
mvn clean install
```
This will build your project and package it into a JAR and place the resulting artifact in the local Maven repository (~/.m2/repository/com/sis/scrum/retrospect/0.0.1-SNAPSHOT/retrospect-0.0.1-SNAPSHOT.jar).
Navigate to the folder containing the jar file and run

```bash
java -jar retrospect-0.0.1-SNAPSHOT.jar
```
## Usage

### H2 In-Memory Database
This project utilizes the H2 in-memory database, making it easy to get started without the need for external database setup. During runtime, dummy data is automatically inserted into the database, allowing you to begin testing the application immediately.

### Swagger UI Integration
To interact with the API and test its functionality, Swagger UI has been seamlessly integrated into the project. Follow these steps to access Swagger UI:

1. Ensure that the application is up and running.

2. Open your web browser and navigate to the following URL:
```bash
http://localhost:8080/swagger-ui/index.html
```
3. The Swagger UI interface provides a user-friendly way to explore the available endpoints, send requests, and view responses. You can use it to test various API functionalities.

### Example API Endpoints
Here are some example API endpoints you can explore using Swagger UI:

GET All Retrospectives: Retrieve a list of all retrospectives.
```bash
/api/v1/retrospective?page=0&size=4
```
POST Create Retrospective: Create a new retrospective.
```bash
/api/v1/retrospective
```
Feel free to explore and test these endpoints using Swagger UI to better understand the capabilities of the application.

## Tests
```bash
# Run tests
mvn test
```
## Contributing

I welcome contributions to improve sis-retrospect! Whether you want to report a bug, request a feature, or contribute code, please follow these guidelines:

### Reporting Issues

If you encounter a bug or have a feature request, please open an issue on my [GitHub Issues](https://github.com/weezyjagbabe/sis-retrospect/issues) page. Before opening a new issue, please search existing issues to see if the same or similar issues have already been reported.

### Feature Requests

If you have an idea for a new feature or improvement, please create an issue on our [GitHub Issues](https://github.com/weezyjagbabe/sis-retrospect/issues) page. Describe the feature or improvement you'd like to see and provide any relevant details.

### Contributing Code

1. Fork the repository to your GitHub account.
2. Clone the forked repository to your local machine:

```bash
git clone https://github.com/weezyjagbabe/sis-retrospect.git
```
1. Create a new branch for your feature or bug fix:
```bash
git checkout -b feature-name
```
2. Make your changes and commit them with clear and concise commit messages:
```bash
git commit -m "Description of your changes"
```
3. Push your changes to your GitHub fork:
```bash
git push origin feature-name
```
4. Create a pull request (PR) from your forked repository to the original repository on GitHub.

5. Please make sure to follow the code style and conventions used in the project. If there are specific coding guidelines or requirements, they will be mentioned in the project's documentation.

6. Your PR will be reviewed by the project maintainers. Be prepared to address any feedback or changes requested during the review process.

### Code of Conduct
We expect contributors to adhere to our Code of Conduct. Please read it before participating in the project.

Thank you for contributing to sis-retrospect!


## License
This project is licensed under the MIT License - see the LICENSE.md file for details.
