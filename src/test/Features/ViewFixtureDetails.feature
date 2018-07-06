@MVP
Feature: View fixture details - Overview


  Background:
      Given I logged in to the Team App by user "jianghongbo515@gmail.com"
    #Given there is at least "1" "upcoming fixture" for my "Team"
    #And I selected a "Team" on the "My Teams" screen

 
  @RCY-147 @test @fixturedetails @regression @sanity
  Scenario Outline: Show fixture details - upcoming

    #When I select an upcoming "Fixture" listed under my team
        Given I'm on "Find a team" page 
            When Find team "Karaka RFC"
            And Select search result "Karaka RFC"
            And Select grade "Karaka Red Under 5"
            And Select team "Karaka Red Under 5"
            When I should go to the upcoming fixtures section
            And Select first game
    Then I should see the following details for the "fixture": 
      |competition_name  |round  | match_date |kickoff_time  |my_organisation  |my_team  |opponent_organisation  |opponent_team  |ground  |field|
      |<competition_name>|<round>|<match_date>|<kickoff_time>|<my_organisation>|<my_team>|<opponent_organisation>|<opponent_team>|<ground>|<field>|
    
    Examples:
      |competition_name     |round  |my_organisation      |my_team         |opponent_organisation|opponent_team   |match_date        |kickoff_time |field    |ground             |
      |Under 5              |Round 1|Whakatane Silver     |Whakatane Silver|Karaka Red           |Karaka Red      |Sat 30th Dec 2017 |9:15am       |Karaka   |372 Blackbridge Rd |
