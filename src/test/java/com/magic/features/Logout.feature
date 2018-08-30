Feature: Title of your feature
  I want to use this template for my feature file

  Scenario: Regex Parametrization
    Given Go to https://www.google.com
    Then enter "akashKansal" and "67890"
    And click on google search
    
    Scenario: Anoher one
    Given Go to https://www.google.com
    Then wait for page load
    Then enter data in google seacrh field
      | first   | second  | third   | fourth  |
      | adka    | second  | third   | fourth  |
      | adsasds | ASDASsa | Adasdsa | asdasas |
    And click on google search
