Feature: New Login Flow

  @RCY-221 @test @login @regression @sanity @sanity
  Scenario: Login with email address --Successful
    Given I open the Team App
    When I enter with my "jianghongbo515@gmail.com" and click contitue button
    #Then I see the message "Welcome back" "FirstName"
    When I enter the password "Test123"
    Then I should be able to login

  @RCY-221 @test @login @regression  
  Scenario Outline: Invalid login attempt with email address
    Given I open the Team App
      When I enter with my "<email>" and click contitue button
      Then I see the error message "<error_message>"
    Examples:
      |email                   |error_message         |
      |userNotexist@mailinator.com|Couldn't find your NZ Rugby Account|
      |                        |Email can't be blank  |
      |sansa                   |Invalid email address |
      |sansa@gmailcom          |Invalid email address |
      |sansa@gmail.            |Invalid email address |
      |              @gmail.com|Invalid email address|

    #accepted email address format: _____@_____.____


  @RCY-221 @test @login @regression 
  Scenario Outline: Login with email address -- wrong password

    Given I open the Team App
      When I enter with my "<email>" and click contitue button
      #Then I see the message "Welcome back" "FirstName"
      When I enter the password "<password>"
      Then I see the error message "<error_message>"
    Examples:
      |email                  |password      |error_message|
      |sabrovin@mailinator.com|              |Password must be 6 character minimum|
      |sabrovin@mailinator.com|test|Password must be 6 character minimum|
      |sabrovin@mailinator.com|wrong_password|Wrong email or password.|


  @RCY-138 @test @logout @login @regression @sanity
  Scenario: Logout of the Team App
    Given I logged in to the Team App by user "jianghongbo515@gmail.com"
    When I log out
    Then I see the page Login
    #And I can not access any other functionality


  @RCY-138 @test @logout @login @regression @sanity
  Scenario: Reopen Team App after Logout
    Given I logged out
    And I close the app on my device
    When I open the app
    Then I see the page Login
    #And I can not access any other functionality
    
