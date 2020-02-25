# TicketService, a test task for Fintech Band

    The project is a simple emulator of an application which receives
    data about a ticket and handles payment process by sending requests
    to a payment gateway service which is also a part of current application.
    
#### Technical details:
    - Used Spring Boot and Maven for project setup;
    - Spring Data and H2 inmemoryDB for persistant data;
    - Spring WebClient for handling http requests;
    - Validation, Lombok, slf4;
    
#### To start and use the application:
    - Make sure you have installed H2 database in order to allow proper H2 db creation;
    - Make sure your Spring Boot launches with these default ULR and port:
    http://localhost:8080
    - Now you may run the project. Go to http://localhost:8080/tickets in your Postman
    or whatever API test call environment you are using and send POST request in JSON,
    for instance:
    {
        "routeNumber": "1", 
        "departureDateTime": "2020-03-03T10:15"
    }
    "routeNumber" should not be empty to pass the validation.
    "departureDateTime" should be written using format YYYY-mm-ddThh:mm,
    should be future and not empty to pass the validation.
    As a response to your POST request you'll receive ticket id, 
    copy that id to a clipboard.
    Now make a GET request to http://localhost:8080/tickets/id, 
    where id - is your ticket id.
    You'll see how the status of your ticket is processed from "NEW" 
    (in case you do GET request within 60 seconds after POST request)
    to either "IN PROGRESS", "ERROR" or "SUCCESS";
