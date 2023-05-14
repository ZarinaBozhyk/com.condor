Feature: UI Testing

  Background:
    Given I open Condor Main page
    Then I check Condor "Main" page is opened

  @ui
  Scenario: User opens Customer Support Page
    When I click on Customer Support button
    Then I check Condor "Customer Support" page is "opened"

  @ui
  Scenario: User searchs for the flights
    And I click on "From" input
    And I type "Bristol" into departure input field
    And I type "Florida" into destination input field
    And I click on "One way" button
    And I select available day
    And I click on Continue button
    Then I check Condor "Outbound Flight" page is opened



