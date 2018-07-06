Feature: Record player position
In order to analyze player performance, as Coach, I want to record the positions played by each player during the match

#Background:
#    Given it is not the first time I use Team App
@test
Scenario Outline: Assign player position
#Given my "userrole" has the "permission" to submit scores 
#And I am on the "Team" tab for a "Fixture"
#When I click assign for the "<positions>"
#Then the "Find player" screen should prompt
#When I input the keyword name "Winston" for player
#Then the player names with "Winston" listed
#When I choose the name "Winston Allen"
#And Add to team sheet
#Then the player for "<position>" is assigned to "Winston Allen"

    Given I skip the welcome page
    And I'm on "Find A CLUB OR SCHOOL" page
    And I input the keyword for my school or club "Te Aroha Junior Rugby"
    And I select a club or school "Te Aroha Junior Rugby"
    And select the corresponding grade "Under 11"
    And I select a team "Blue"
    When I add team to my teams
    And Select first game
    When I am on the "Team" tab
    And I click assign for the "<positions>"
    Then the "Find player" screen should prompt
Examples:
 |positions          |
 |Loosehead Prop     |
 |Hooker             |
 | Tighthead Prop    |
 | Lock              |
 | Blind-side Flanker|
 | Number 8          |
 | Scrum Half        |
 | Outside Half      |
 | Left Wing         |
  |left centre|
|right centre|
|Rignt wing |
|Full back |
|Reserves|

Scenario Outline: Change player position
Given my "userrole" has the "permission" to submit scores 
And I am on the "Team" screen for a "Fixture"
And the player "Winston Allen" is assigned to "<positions>"
When I click change for the "<positions>"
Then the "Find player" screen prompt
When I input the name "Barness" for player
Then the player names with "Barness" listed
When I choose the name "Winston Barness"
And Add to team sheet
Then the player for "<position>" is changed to "Winston Barness"
Examples:
 |positions          |
 |Loosehead Prop     |
 |Hooker             |
 | Tighthead Prop    |
 | Lock              |
 | Blind-side Flanker|
 | Number 8          |
 | Scrum Half        |
 | Outside Half      |
 | Left Wing         |
  |left centre|
|right centre|
|Rignt wing |
|Full back |
|Reserves|

Scenario: Record player position -- player not found
Given my "userrole" has the "permission" to submit scores 
And I am on the "Team" screen for a "Fixture"
And there's no "Richard" in player's name
When I click assign for the "<positions>"
Then the "Find player" screen prompt
When I input the name "Richard" for player
Then I should see warning "Sorry we couldn't find the player"
