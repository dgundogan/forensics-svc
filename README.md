# 'Forensics' Rest Service

`forensics-svc` is a REST microservice application.

## Implementation

Following tech spec is used for the TDD based implementation.

- *Kotlin*
- *Spring Boot*
- *Gradle*
- *JUnit*

The project is organized as a *Gradle* project and in order to compile, test and create a package *Gradle* is used.

### Building the application

You could use Gradle to test and build the jar file.

* In the root directory of the project run the following commands

```bash
# Build
./gradlew clean build

#Test
./gradlew clean testClasses


```

## Using the API

### Running the application

* Start the application with the following command:

```bash

#Run
./gradlew run

```


### Request

1) Get Direction Usage:

|End Point                                                                   | Operation |Port  |
|----------------------------------------------------------------------------|-----------|------|
|http://localhost:8080/api/{email}/directions                                |GET        | 8080 |



Example:
http://localhost:8080/api/test@domain.com/directions


2) Get Location Usage:

|End Point                                                                                      | Operation |Port  |
|-----------------------------------------------------------------------------------------------|-----------|------|
|http://localhost:8080/api/{email}/location/{x}/{y}                                             |GET        | 8080 |


Example:
http://localhost:8080/api/test@domain.com/location/100/100


### Direction setup
You can set up the direction in application.yml

### Response

#### Response Model

```bash
{
        "status": "FAIL",
        "message": "Please check Direction API"
}
```

#### Error Model

```bash
{
    "message": "Invalid Email"
}
```
