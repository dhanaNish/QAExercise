@SearchInVacancies
Feature: Search for a specific vacancy
  As a User
  I want to be able to searchon the open vacancies
  So that I can quickly find the open vacancies in Openbet that Im interested in
  
  Scenario: User checks for keywords input field in Vacancies page
    Given user is on Vacancies page
    When user checks for the keywords input field
    Then user can find and edit in the keywords input field 
    
  Scenario: User can check for specific vacancy in Vacancies page
  	Given user is on Vacancies page
  	When user edits the keywords field and press enter
  	Then new results based on search are displayed in the Vacancies page 
  
  Scenario: New results are displayed only after pressing Enter in the keywords field
  	Given user is on Vacancies page
  	When user enter value in the keywords field 
  	Then search does not happen in the Vacancies page
  	
  Scenario Outline: Special characters entered in the keywords input field is ignored 
  	Given user is on Vacancies page
  	When user enters special characters in the "<keywords>" field and press enter
  	Then keywords field ignores special character and populates results

  	 Examples: 
      | keywords  | 
      | te$st     |
      | le^ad     | 