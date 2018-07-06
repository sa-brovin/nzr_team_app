Feature: Show my teams

  In order to simplify personalisation of the Team App for me,
  as a Team Member,
  I want to be automatically associated with the teams that I manage or play in

#  Background:
#      Given I logged in to the Team App by user "jianghongbo515@gmail.com"

  @RCY-136 @test @ShowMyTeams @regression @sanity @find
  Scenario Outline: Follow team
    #Given Clear my teams
    #And I am on the Menu page
    #And I select "Find a team"
    Given I skip the welcome page
    And I'm on "Find A CLUB OR SCHOOL" page
    And I input the keyword for my school or club "<Club>"
    And the club or school including the keyword should be listed "<Club>"
    And I select a club or school "<Club>"
    And select the corresponding grade "<Grade>"
    And I should be able to see the list of teams
    And I select a team "<Team>"
    When I add team to my teams
    Then I should be able to see the team in my team list
    And Clear my teams
    
    Examples:
    |Club|Grade|Team|
    |Te Aroha Junior Rugby|Under 11|Blue|
    # |Hauraki Plains Youth Rugby Club|Under 13|Hauraki Plains|

    

  @RCY-136 @test @ShowMyTeams @regression @debug1
  Scenario Outline: Show Team list  -- no upcoming game
	
	 Given I skip the welcome page
	 And Clear my teams
   	When that I followed more than one team
     Then I should be redirected to the "My Teams" screen
    And I should see the icon of the club or school "<team_title>"
    Then I should see the "<team_name>" "<team_title>" "<team_grade>" "<next_game_date>"

  Examples:
    |team_name            |team_title           |team_grade|next_game_date|
    |Tawa Rugby Football Club|Premier|Premier|Sat 31st Mar 2018|
#    |Te Aroha Junior Rugby|Te Aroha U11|Under 11|none|
#    #|Hauraki Plains Youth Rugby Club|Under 13|
#    #| Takapuna Rugby Football Club|Karaka Black|Under 7     |Fri 1st Dec 2017 |


    
    
    Scenario Outline: Show Team list -- has next game
    Given Clear my teams
    And I skip the welcome page
    And that I followed more than one team
    #When I open the Team App
    Then I should be redirected to the "My Teams" screen
    And I should see the icon of the club or school "<team_title>"
    And I should see the "<team_name>" "<team_title>" "<team_grade>" "<next_game_date>"
    And Clear my teams
  Examples:
    |team_name|team_title|team_grade|next_game_date |
    |Te Aroha Junior Rugby|Under 11|Blue|None|
    |Hauraki Plains Youth Rugby Club|Under 13|
    #| Takapuna Rugby Football Club|Karaka Black|Under 7     |Fri 1st Dec 2017 |
    
