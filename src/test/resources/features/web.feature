Feature: UI Testing

  Background:
    Given I open Condor Main page
    Then I check Condor "Main" page is opened

  @ui
  Scenario: User opens Customer Support Page
    When I click on Customer Support button
    Then I check Condor "Customer Support" page is "opened"

  @ui
  Scenario Outline: User searchs for the flights
    And I click on "<input>" input
    And I type "<departure>" into departure input field
    And I type "<destination>" into destination input field
    And I click on "<button>" button
    And I select available day
    And I click on Continue button
    Then I check Condor "Outbound Flight" page is opened
    Examples:
      | input | departure | destination | button  |
      | From  | Bristol   | Florida     | One way |



