
@NavigateVacancies
Feature: Navigate Vacancies
  As a User
  I want to be able to quickly navigate to the open vacancies page
  So that I can quickly view the open vacancies in Openbet 


  Scenario: Vacancies menu is present in homepage
    Given user is on homepage
    When user checks for Vacancies menu in top of homepage 
    Then Vacancies menu is present in homepage header section



  Scenario: Navigate to Open vacancies page 
    Given user is on homepage
    When user clicks on Vacancies menu
    Then user is navigated to Open vacancies page
   
   
 
   