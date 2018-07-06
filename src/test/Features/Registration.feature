Feature: New Registration Flow


 Background:
    Given I open the Team App
    And I am on the Register Screen in Team App


#  @RCY-139 @RCY-220
#  Scenario Outline: Register with Email  - successful
#    Given I open the Team App
#   
#        And I am on the Register Screen in Team App
#        And I input a valid email
#        When I enter "<password1>" and re-enter "<password2>"
#        And I tick the option "I agree to our Terms & Conditions"
#        And I click "Create Account"
#        Then I should see message "Click on the link in the email to continue"
#        And I get an Email with instructions to verify my account
#        When I confirm the account in my email
#        Then I should be redirected to the successful verification page
#        And  I am on the sign in page of the TeamApp after several seconds
#       
#    Examples:
#      |password1|password2|
#      |Test123456|Test123456|

  @RCY-220 @test
  Scenario Outline: Register with Email  - successful
    
    When I input a valid email
    And I enter "<password1>" and re-enter "<password2>"
    And I tick the option "I agree to our Terms & Conditions"
    And I click "Sign Up"
    Then I should see message "To complete your registration click on the link we’ve just emailed to"
    # And I get an Email with instructions to verify my account
    Examples:
      |password1|password2|
      |Test123456|Test123456|
     
   @RCY-220 @test
     Scenario Outline: 
     Register with Email  - Attempt to sign up with invalid email 
     When I input an invalid "<email>"
     Then I see the error message "<error_message>"
     Examples:
        |email                   |error_message         |
        |                        |Email can't be blank  |
        |sansa                   |Invalid email address |
        |sansa@gmailcom          |Invalid email address |
        |sansa@gmail.            |Invalid email address |
        |              @gmail.com|Invalid email address|
#accepted email address format: _____@_____.____  
      
      
   @RCY-220 @test        
    Scenario Outline: Register with Email  - Attempt to sign up with invalid password   
    When I input a valid email
	And I enter "<password1>" and re-enter "<password2>"
	And I tick the option "I agree to our Terms & Conditions"
	And I click "Continue"
	Then I see the error message "<error_message>"
	Examples:
      |password1|password2|error_message|
      |        |        |Password can't be blank|
      #To do: failed. no error message for blank password
      |pw|pw|Password must be 6 character minimum|
      |Password1|Password2|Passwords don't match|
  
    @RCY-220 @test 
  	Scenario:  Attempt to register with existed Email 
	When I input an existed account "jianghongbo515@gmail.com"
	Then I see the error message "Welcome Back"
#. Please enter your password for" 
	When I enter the password "Test123"
    Then I should be able to login
   
     
    @RCY-220 @test
    Scenario Outline: Register with Email  - Attempt to sign up without tick terms & conditions
    Given I open the Team App
    And I am on the Register Screen in Team App
    And I input a valid email
    When I enter "<password1>" and re-enter "<password2>"
    And I click "Sign Up"
    Then I see the error message "You must agree the terms and conditions"
    
        Examples:
      |password1|password2|
      |Test123456|Test123456|