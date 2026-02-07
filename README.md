# Java Assessment – Submission

## DISCLAIMER⚠️
I'm building this project directly using GitHub Codespaces as the primary developmnet environment.

Please note the following: 
- The project was developed and tested in a cloud-based IDE (GitHub Codespaces) rather than a local machine.
- Tooling such as Java version, Maven, and port forwarding relies on the Codespaces environment configuration.
- API testing was performed using tools such as curl / Postman-style requests via forwarded ports in Codespaces.

## Overview

This repository contains my solution for the Java assessment.
The implementation is divided into two parts:
- **Part 1** – Core Java logic (Java standard library only)
- **Part 2** – Spring Boot REST API (Option A)

The main focus of this solution is:
- correctness
- clean and readable logic
- defensive programming
- strict adherence to the assessment requirements
without unnecessary frameworks or overengineering.

## Part 1 – Core Java Problems
**Problem 1 – Category Tree Builder**
**Objective**
Transform a flat list of categories into a hierarchical tree structure using ```parentId```.

**Thought Process**
- Input order is arbitrary → indexing is required
- Categories are stored in a ```Map``` for fast parent lookup
- Categories with ```parentId == null``` are treated as root nodes
- Children are attached to their parents
- A DFS-based cycle detection ensures no circular references
- Defensive checks handle:
  * null input
  * missing parent
  * circular dependencies
## 

**Problem 2 - Product Filter**
**Objective**
Filter a list of products based on optional criteria:
- minimum price
- maximum price
- category

**Thought Process**
- A base ```Predicate``` is used and combined dynamically
- Avoids complex conditional branching
- Easy to extend if new filters are added

**Key Characteristics**
- Clean and extensible logic
- Uses Java Streams and functional interfaces
- Time complexity: **O(n)**
##

**Problem 3 - Data Reconciliation**
**Objective**
Merge two data source using a unique key with override rules.
**Thought Process**
- Records are indexed by key to avoid nested loops
- Source A is used as the base dataset
- Source B overrides values only when non-null and non-empty
- Defensive copying is used to avoid mutating input data

**Key Characteristics**
- Schema-agnostic
- Correct confilect resolution
- Order-independent result

##Part 2 - Sping Boot (Option A)
**REST API Problem 1**
Endpoint implemented:

```bash
POST /api/transform/tree
```
##
**Architecture**

```controller → service → model```
- **Controller**: Handles HTTP requests
- **Service**: Contains business logic
- **Model**: Represents the domain object
- **Handler**: Handle Exception like invlaid JSON requests

## How to Run the Project
**Prerequisites**
- Java 8
- Maven
##
**Build & Run**
```bash
mvn clean install
mvn spring-boot:run
```
The application will start on:
```arduino
http://localhost:8080
```
##
**API Testing**
Endpoint
```bash
POST /api/transform/tree
```
Headers
```pgsql
Contet-Type: application/json
```
##
Test Request Body
```json
[
  { "id": 1, "name": "Electronics", "parentId": null },
  { "id": 2, "name": "Laptops", "parentId": 1 },
  { "id": 3, "name": "Phones", "parentId": 1 },
  { "id": 4, "name": "Fashion", "parentId": null },
  { "id": 5, "name": "Clothes", "parentId": 4 },
  { "id": 6, "name": "Black Shirt", "parentId": 5 },
  { "id": 7, "name": "Asus ROG", "parentId": 2 },
  { "id": 8, "name": "IPhone 17", "parentId": 3 }
]
```
##
Response Should Be
```json
[
    {
        "id": 1,
        "name": "Electronics",
        "parentId": null,
        "children": [
            {
                "id": 2,
                "name": "Laptops",
                "parentId": 1,
                "children": [
                    {
                        "id": 7,
                        "name": "Asus ROG",
                        "parentId": 2,
                        "children": []
                    }
                ]
            },
            {
                "id": 3,
                "name": "Phones",
                "parentId": 1,
                "children": [
                    {
                        "id": 8,
                        "name": "IPhone 17",
                        "parentId": 3,
                        "children": []
                    }
                ]
            }
        ]
    },
    {
        "id": 4,
        "name": "Fashion",
        "parentId": null,
        "children": [
            {
                "id": 5,
                "name": "Clothes",
                "parentId": 4,
                "children": [
                    {
                        "id": 6,
                        "name": "Black Shirt",
                        "parentId": 5,
                        "children": []
                    }
                ]
            }
        ]
    }
]
```
Tested using Postman
##
If the Response is invalid for example
```bash
curl -X POST http://localhost:8080/api/transform/tree \
  -H "Content-Type: application/json" \
  -d '{ invalid json }'
```
Response should be
```nginx
{
    "message": "Invalid JSON Format!!",
    "code": "X9901"
}
```
##
**Notes**
- Part 1 uses **Java standard library only**
- Part 2 uses **Spring Boot Web**
- no databases or external services are used
