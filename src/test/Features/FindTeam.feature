Feature: Find my team
In order to be able to access my team's details, as a user, I need a way to search teams.

  Background:
    Given I logged in to the Team App by user "jianghongbo515@gmail.com"

  @test @find @regression @sanity 
  Scenario Outline: Search club or school 
    Given I skip the welcome page
    And I'm on "Find A CLUB OR SCHOOL" page
    When Find team "<Club>"
    Then Team "<Club>" should be in search results
    Examples:
    |Club|
    |Hauraki Plains Youth Rugby Club|

  @RCY-219 @test @find @regression 
  Scenario: Search club or school -- no result
    #Given I am on the Menu page
    #And I select "Find a team"
    Given I skip the welcome page
    And I'm on "Find a team" page
    When I input the keyword for my school or club not exists
    Then I should see error message "Sorry we could't find what you are looking for."

  @RCY-219 @test @find @regression @sanity
  Scenario Outline: Find my teams
    #Given I am on the Menu page
    #And I select "Find a team"
    Given I skip the welcome page
    And I'm on "Find a team" page
    When I input the keyword for my school or club "<Club>"
    Then the club or school including the keyword should be listed "<Club>"
    When I select a club or school "<Club>"
    And select the corresponding grade "<Grade>"
    Then I should be able to see the list of teams
    Examples:
    |Club|Grade|
    |Tawa Rugby Football Club|Premier|
    #|Takapuna Rugby Football Club|Presidents|


  @RCY-219 @test @find @regression
  Scenario Outline: Find my team - first time user
    Given I use Team App at the first time
    And I open the Team APP
    And I skip the welcome page
    And I should be redirected to the "Find A CLUB OR SCHOOL" screen
    And I input the keyword for my school or club "<Club>"
    And the club or school including the keyword should be listed "<Club>"
    And I select a club or school "<Club>"
    When select the corresponding grade "<Grade>"
    Then I should be able to see the list of teams
    Examples:
    |Club|Grade|
    |Tawa Rugby Football Club|Premier|

  @RCY-219 @test @find @regression
  Scenario Outline: Find my team - go back
    Given it is not the first time I use Team App
    #And I am on the Menu page
    #And I select "Find a team"
    And I skip the welcome page
    And I'm on "Find a team" page
    When I input the keyword for my school or club "<Club>"
    And the club or school including the keyword should be listed "<Club>"
    And I select a club or school "<Club>"
    And I click go back on the left top corner
    #Then I should be able to go back to the menu page
    Then I should be able to go back to the search page
    Examples:
    |Club|Grade|
    |Tawa Rugby Football Club|Premier|