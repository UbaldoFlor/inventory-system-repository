# Inventory System

This is a Spring Boot application for managing a simple inventory system for a store. It allows users to view a list of available products, add new products, and update existing ones. Each product has a name, description, price, and quantity.

The application uses Hibernate to persist the product information in a MySQL database.

## Requirements

- Java 11 or later
- MySQL 5.6 or later

## Installation

1. Clone this repository.
2. Navigate to the root directory of the project.
3. Update the `application.properties` file located in `src/main/resources` directory to include your MySQL database username and password.
4. Build the application using Maven: `mvn clean install`

## Usage

1. Run the application: `mvn spring-boot:run`
2. Open a web browser and go to `http://localhost:8080` to view the list of products.
3. To add a new product, click the **Add Product** button and fill out the form.
4. To update an existing product, click the **Edit** button next to the product you want to update and make the changes in the form.
5. To delete a product, click the **Delete** button next to the product you want to delete.

## Tests

To run tests, use the following command:

```
mvn test
```

## Feedback

- Was it easy to complete the task using AI? Definitely, it was so much easier to complete the task using AI. I saved a lot of time in writing code.
- How long did task take you to complete? 8 hours, I saved a lot of time writing code, but some difficulties came up and code changes were needed.
- Was the code ready to run after generation? What did you have to change to make it usable? It was not ready to run, I needed to change some annotations, import statements, dependencies, and some directories which were incorrect.
- Which challenges did you face during completion of the task? I found difficult to implement the project because AI omitted some settings, so I checked all the scripts deeply to make sure the application works fine.
- Which specific prompts you learned as a good practice to complete the task? I learned that it's important to establish a well-structured context with specific data and output to get the desired result.
