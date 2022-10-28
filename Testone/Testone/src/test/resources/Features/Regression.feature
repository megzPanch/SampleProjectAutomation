@Test
Feature: Selenium Regression Flow

  Background: 
    Given A workbook named "TESTDATA" and sheet named "Result" is read

  Scenario Outline: verify new browser window
    Given user launch the application <URL>
    And user validate new browser window button

    Examples: 
      | URL |
      | url |

  Scenario: verify new browser tab
    Then user validate new browser tab button

  Scenario: verify alert button
    Then user validate alert

  Scenario Outline: verify feedback form
    Then user validate feedback form with <Name> <Email> <Phone> <Country> <Company> <Messeage>

    Examples: 
      | Name | Email          | Phone      | Country | Company | Messeage |
      | Megz | megan@gmail.co | 1234567890 | India   | XYZ     | Testing  |
