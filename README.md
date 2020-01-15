# repayment-plan-generator

Service to calculate repayment plans for annuity loans.

## Prerequisites

* Java 8

## Optional 
* Docker

## Usage
1) Clone the repository from ([here](https://github.com/tdhraik/repayment-plan-generator.git))
2) cd repayment-plan-generator
3) Compile and run all the tests using ```./mvnw clean install```
4) Run application locally using ```./mvnw spring-boot:run```
5) Or build it and run it without Maven
``` shell
./mvnw package
java -jar target/repayment-plan-generator-1.0.jar
```

## Usage(docker)
1) Clone the repository from ([here](https://github.com/tdhraik/repayment-plan-generator.git))
2) cd repayment-plan-generator
3) Run the following commands...
 ```
 docker image build -t repayment-plan-generator .
 docker run -p 8080:8080 repayment-plan-generator
 ```
 
Application should be running now...

## Swagger
1) Once the application is up, open http://localhost:8080/swagger-ui.html to access the end points.
2) ``` /generate-plan (POST) ```

``` shell
payload
{
    "loanAmount": "5000",
    "nominalRate": "5.0",
    "duration": 24,
    "startDate": "2018-01-01T00:00:01Z"
}

response
{
  [
    {
    "borrowerPaymentAmount": "219.36",
    "date": "2018-01-01T00:00:00Z",
    "initialOutstandingPrincipal": "5000.00",
    "interest": "20.83",
    "principal": "198.53",
    "remainingOutstandingPrincipal": "4801.47",
    },
    {
    "borrowerPaymentAmount": "219.36",
    "date": "2018-02-01T00:00:00Z",
    "initialOutstandingPrincipal": "4801.47",
    "interest": "20.01",
    "principal": "199.35",
    "remainingOutstandingPrincipal": "4602.12",
    },
    ...
    {
    "borrowerPaymentAmount": "219.28",
    "date": "2020-01-01T00:00:00Z",
    "initialOutstandingPrincipal": "218.37",
    "interest": "0.91",
    "principal": "218.37",
    "remainingOutstandingPrincipal": "0",
    }
  ]
}
```
