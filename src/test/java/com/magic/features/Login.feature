Feature: this is for learning
  then after learning will implement in framework

  Background: Setting up browser and Driver
    Given Enter the browser name and keep it synchronized
    Then set the driver in a thread

  Scenario: Regex Parametrization
    Given Go to https://www.google.com
    Then enter "akashKansal" and "67890"
    And click on google search

  Scenario: searching on google homepage
    Given Go to https://www.google.com
    Then wait for page load
    Then enter data in google seacrh field
      | first   | second  | third   | fourth  |
      | adka    | second  | third   | fourth  |
      | adsasds | ASDASsa | Adasdsa | asdasas |
    And click on google search

  @Smoke
  Scenario Outline: searching on google homepage
    Given Go to https://www.google.com
    Then wait for page load
    Then enter data in google seacrh field <username> <password>
    And click on google search

#    @Smoke
#    Examples: 
#      | username  | password  |
 #     | data1 | data21 |
  #    | data2 | data22 |
   #   | data3 | data23 |
    #  | data4 | data24 |

    @Reg
    Examples: 
      | data  | dataa  |
      | data1 | data21 |
