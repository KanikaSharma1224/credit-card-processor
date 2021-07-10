# credit-card-processor

Two REST Endpoints are implemented
    • "Add" will create a new credit card for a given name, card number, and limit
        ◦ Card numbers should be validated using Luhn 10
        ◦ New cards start with a £0 balance
        ◦ for cards not compatible with Luhn 10, return an error
    • "Get all" returns all cards in the system

## mvn clean install to build the project.

# GET /api/v1/cards  
      to get list of all cards
      
# POST /api/v1/cards 

{
  "cardNumber":"4532837128802893",
  "cardHolderName":"Test"
}
