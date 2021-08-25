#Author: maharaja.mpj@gmail.com
#Keywords Summary :Validation of Users API


Feature: Users API's Validation


  @sanity1
  Scenario: Validate the List of Users
    Given I have API endpoint
    When I send GET request
    Then I validate the response code
    Then I validate the last name
    
      @sanity1
  Scenario Outline: Validate the List of Users
    Given I have API endpoint
    When I send the request
    Then I validate the distance from <lat> and <lon>
    Examples:
    | lat       | lon | 
    | 13.067439 | 80.237617 |  
      @sanity
  Scenario: Validate the List of Users
    Given I have API endpoint
    When I send GET request
    Then I validate the schema
    Then I validate the created time


