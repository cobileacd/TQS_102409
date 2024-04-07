@blazeDemo_sample

Feature: Purchase a flight
  To allow a customer to buy a flight from and to a place of their choice 
 
  Scenario: Buy one flight ticket
      When I navigate to "https://www.blazedemo.com/"
        And I select "Boston" on the "fromPort" input
        And I select "London" on the "toPort" input
        And I click "Find Flights"
        Then I should be redirected to a page with the title "BlazeDemo - reserve"
      When I click "Choose This Flight" on flight 43
        Then I should be redirected to a page with the title "BlazeDemo Purchase"
      When I select "JOJO" on the "inputName" input
        And I click "Purchase Flight"
        Then I should be redirected to a page with the title "BlazeDemo Confirmation"