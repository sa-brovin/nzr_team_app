Feature: Welcome Page

  #Background:
    #Given I logged in to the Team App by user "jianghongbo515@gmail.com"

  @test @find @regression @sanity
  Scenario: Welcome page for first time user 
  Given I use Team App at the first time
  And I open the Team APP
  When I view the introduction of Team App
  And I get started
  Then I should redirect to "Find A CLUB OR SCHOOL" page
  
  Scenario: Skip the welcome page
  Given I use Team App at the first time
  And I open the Team APP
  When I skip the welcome page
  Then I should redirect to "Find A CLUB OR SCHOOL" page
  

    
