Feature: View my fixtures
In order to plan my season, as a user, I want to be able to view a list of fixtures for each team that I am interested in.
 Background:
    Given Clear my teams
    And I skip the welcome page
    And that I followed more than one team
   
 
  @RCY-146 @test @viewmyfixtures @regression @sanity
  Scenario Outline: Show upcoming games


    When I select a team "<my_team>"
    Then I should go to the upcoming fixtures section       
	Then I should see the "<team_name>" "<team_title>" "<team_grade>" "<next_game_date>"
    And I should see all "upcoming fixtures" for my "Team" with the following details per fixture:
#      |competition_name  |round  |match_status  | match_date      |my_organisation  |my_team  |opponent_organisation  |opponent_team  |ground  |
#      |<competition_name>|<round>|<match_status>|<match_date>     |<my_organisation>|<my_team>|<opponent_organisation>|<opponent_team>|<ground>|
      |round  |match_status  | match_date      |my_organisation  |my_team  |opponent_organisation  |opponent_team  |ground  |
      |<round>|<match_status>|<match_date>     |<my_organisation>|<my_team>|<opponent_organisation>|<opponent_team>|<ground>|
        Examples:
      |Club|Grade|competition_name|round  |match_status|match_date   |my_team      |opponent_team|ground|
      |Te Aroha Youth|Under 11|Te Aroha U11 Black|Round 4|Confirmed|Sat 10 Mar 2018|Te Aroha U11 Black|RED|South Hill|
      #|Hauraki Plains|Round 1|Confirmed|Sat 24th Mar 2018|Hauraki Plains|girls 1stxv      |Premier 1B   |Mount Eden |  



 Scenario Outline: Show upcoming games1
    #Given there is at least "1" "upcoming fixture" for my "Team"
    #When I selected a "Team" on the "My Teams"
    #Then I should go to the "upcoming fixtures" section
    Given I skip the welcome page
    And I'm on "Find A CLUB OR SCHOOL" page
    And I input the keyword for my school or club "<Club>"
    And the club or school including the keyword should be listed "<Club>"s
    And I select a club or school "<Club>"
    And select the corresponding grade "<Grade>"
    And I should be able to see the list of teams
    When I select a team "<my_team>"
    Then I should go to the upcoming fixtures section
    Then I should see all "upcoming fixtures" for my "Team" with the following details per fixture:
#      |competition_name  |round  |match_status  | match_date      |my_organisation  |my_team  |opponent_organisation  |opponent_team  |ground  |
#      |<competition_name>|<round>|<match_status>|<match_date>     |<my_organisation>|<my_team>|<opponent_organisation>|<opponent_team>|<ground>|
      |round  |match_status  | match_date      |my_organisation  |my_team  |opponent_organisation  |opponent_team  |ground  |
      |<round>|<match_status>|<match_date>     |<my_organisation>|<my_team>|<opponent_organisation>|<opponent_team>|<ground>|
      
    
    Examples:
      |Club|Grade|competition_name|round  |match_status|match_date   |my_team      |opponent_team|ground|
      |Te Aroha Youth|Under 11|Te Aroha U11 Black|Round 4|Confirmed|Sat 10 Mar 2018|Te Aroha U11 Black|RED|South Hill|
      #|Hauraki Plains|Round 1|Confirmed|Sat 24th Mar 2018|Hauraki Plains|girls 1stxv      |Premier 1B   |Mount Eden |
      

    
#    And I should see all "upcoming fixtures" for my "Team" with the following details per fixture:
#    Examples:
#   #   |<competition_name>|<round>|<match_status>|<match_date>     |<my_organisation>|<my_team>|<opponent_organisation>|<opponent_team>|<ground>|
#    |Club                           |Grade   |Team          |<competition_name>     |<round>|<match_status>|<match_date>     |<opponent_organisation>|<opponent_team>|<ground>|
#    |Hauraki Plains Youth Rugby Club|Under 13|Hauraki Plains|Hauraki Plains Under 13|Round 1|Confirmed     |Sat 24th Mar 2018|Whakatane Silver|U13 Te Aroha       |Karaka red   |karaka |
   
      

  @RCY-146 @test @viewmyfixtures @regression @sanity
  Scenario: No upcoming fixtures available
    #Given there is no upcoming fixture for my "Team"
    #When I select a "Team" on the "My Teams"
    #And I go to the "Upcoming fixtures page"
    Given I'm on "Find a team" page
    When Find team "Karaka RFC"
    And Select search result "Karaka RFC"
    And Select grade "Karaka Black Under 7"
    And Select team "Karaka Black Under 7"
    When I should go to the upcoming fixtures section
    Then I should see a message "There are no upcoming games for this team."

  @RCY-146 @test @viewmyfixtures @regression @sanity
  Scenario Outline: Show played games
    #Given there is at least "1" "played fixture" for my "Team"
    #When I selected a "Team" on the "My Teams"
    Given I'm on "Find a team" page
        And Find team "Karaka RFC"
        And Select search result "Karaka RFC"
        And Select grade "Karaka Red Under 5"
        And Select team "Karaka Red Under 5"
        When I should go to the played fixtures section
    Then I should see all "upcoming fixtures" for my "Team" with the following details per fixture:
      |competition_name  |round  |match_status  | match_date      |my_organisation  |my_team  |opponent_organisation  |opponent_team  |ground  |
      |<competition_name>|<round>|<match_status>|<match_date>     |<my_organisation>|<my_team>|<opponent_organisation>|<opponent_team>|<ground>|

    Examples:
      |competition_name   |round  |match_status|match_date       |my_organisation     |my_team   |opponent_organisation|opponent_team|ground |
      |Karaka Red Under 5 |Round 1|Confirmed   |Fri 1st Dec 2017 |karaka red          |karaka red|Star black           |Star black   |Karaka |

 @RCY-146 @test @viewmyfixtures @regression
  Scenario: No Played fixtures available
    #Given there is no played fixture for my "Team"
    #When I select a "Team" on the "My Teams"
    #And I go to the "Played fixtures page"
    Given I'm on "Find a team" page
    And Find team "Karaka RFC"
    And Select search result "Karaka RFC"
    And Select grade "Karaka Black Under 7"
    And Select team "Karaka Black Under 7"
    When I should go to the played fixtures section
    Then I should see a message "This team has not played any games yet."
