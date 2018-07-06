Feature: Select roles

  Background:
    Given I logged in to the Team App by user "jianghongbo515@gmail.com"

  @test @find @regression @sanity 
  Scenario Outline: User select a role to use TeamApp
    Given I skip the welcome page
    When I choose my "<Roles>"
    |Player|
    |Coach|
 	And I tick the option "I agree to our Terms & Conditions"
	And I click "Continue"
	
    And I'm on "Find A CLUB OR SCHOOL" page
    When Find team "<Club>"
    Then Team "<Club>" should be in search results
    Examples:
    |Club|
    |Hauraki Plains Youth Rugby Club|