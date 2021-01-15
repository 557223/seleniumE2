Feature: Login Features
  Verify if user is able to book hotel

  Scenario: Login as a authenticated user
    Given url launch and verify user is on homepage
    When user clicks on login without entering username and password verify error on username
    When user clicks on login without password verify error on password
    When user enters username as "admin" and password as "admin"
    Then verify login is successful and Hotel Application form page opened
    When enter confirm booking without entering first Name verify alert message as "Please fill the First Name"
    When enter confirm booking without entering last Name verify alert message as "Please fill the Last Name"
    When enter confirm booking without entering email verify alert message as "Please fill the Email"
    And  enter confirm booking with wrong email pattern and verify alert message as "Please enter valid Email Id."
    When enter confirm booking without entering mobile number verify alert message as "Please fill the Mobile No."
    And  enter confirm booking with wrong mobile number pattern and verify alert message as "Please enter valid Contact no."
    When entered address details
    Then verify alert when city is not selected as "Please select city" and then select city "Hyderabad"
    Then verify alert when state is not selected as "Please select state" and then select state "Telangana"
    Then verify no of rooms changes as per no of guests selection
    Then verify alert when Card Holder name is not entered with alert message as "Please fill the Card holder name"
    Then verify alert when debit card number is not entered with alert message as "Please fill the Debit card Number"
    Then verify alert when CVV is not entered with alert message as "Please fill the CVV"
    Then verify alert when Expiration month is not entered with alert message as "Please fill expiration month"
    Then verify alert when Expiration year is not entered with alert message as "Please fill the expiration year"
    Then confirm booking after entering all data and validate booking success
    Then close driver
