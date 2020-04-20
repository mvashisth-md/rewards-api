# rewards-api

This application returns rewards earned by customer over three months, monthly as well as total rewards. 
When application starts it loads some test data in H2 in memory database for testing purpose. 
To add more test data, another api end point has been exposed so more dynamic transaction test data can be added which will be reflected in further get reward api calls.


## Instructions to use this Api

1. Build Using Gradle Wrapper (When using command prompt instead of IDE where your current directory is project folder).
   Make sure gradlew has executable permission. 
```./gradlew build```
The first time you run the wrapper script, gradlew, there may be a delay while used version (5.5.1) of gradle is downloaded and stored locally.

2. Run the Application Using Gradle Wrapper (When using command prompt instead of IDE)
```
./gradlew bootRun
```
3. Swagger URL

http://localhost:8080/rewards-api/swagger-ui.html

4. To get Rewards by customer Id, use this api endpoint: 

```http://localhost:8080/rewards-api/customers/{customerId}/rewards ```                
e.g:
http://localhost:8080/rewards-api/customers/C1/rewards

Response :

```json
{
  "customerId":"C1",
  "monthlyRewards":{
    "2020-03":590,
    "2020-02":0
  },
  "totalRewards":590
}

```
5. To get Rewards of all customers, use this api endpoint: 
http://localhost:8080/rewards-api/customers/rewards
```json
[
  {
      "customerId":"C1",
      "monthlyRewards":{
      "2020-03":590,
      "2020-02":0
      },
      "totalRewards":590
   },
   {
      "customerId":"C2",
      "monthlyRewards":{
      "2020-04":30,
      "2020-02":170
      },
      "totalRewards":200
    },
    
    {
      "customerId":"C3",
      "monthlyRewards":{
      "2020-04":440,
      "2020-02":250
      },
      "totalRewards":690
    },
    
    {
      "customerId":"C4",
      "monthlyRewards":{
      "2020-04":130
      },
      "totalRewards":130
    }
]

```
6. To create/save more test data, use this POST api endpoint in Swagger: 
http://localhost:8080/rewards-api/customers/{customerId}/transaction

```json
{
  "amount": 150,
  "transactionTs": "2020-04-19T10:20:30.678Z"
}

```

## Technologies Used 

Java 8, Spring Boot, H2 database, Gradle, Junit-5 and Mockito.
