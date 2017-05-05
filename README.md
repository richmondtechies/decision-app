# decision-app
To compile and execute this application use below command

  mvn lagom:runAll

To use Offer API

  http://localhost:57629/api/card/offer
  Input body - {"name": "Raja","ssn":"1111","income":"100785","dob":"02-03-1998"}
 Get the ackId from above call

To use decision API:

 http://localhost:57629/api/card/decision
  input body {"ackId":"5702f9ab-69dc-4241-9d7e-b41ba71015a6"}
 
 
