#Author: maharaja.mpj@gmail.com
#Keywords Summary :Validation of Users API


Feature: Users API's Validation


  @sanity
  Scenario: Validate the List of Users
    Given I have API endpoint
    When I send the request
    Then I validate the response code


