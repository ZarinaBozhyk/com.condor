Feature: API Testing

  @api @withContext
  Scenario Outline: Users data validation (comments and emails)
    When I get the list of users
    Then I check that list of users contains user names as below:
      | <user_name>       |
      | Antonette         |
      | Samantha          |
      | Karianne          |
      | Kamren            |
      | Leopoldo_Corkery  |
      | Considine-Lockman |
      | Elwyn.Skiles      |
      | Maxime_Nienow     |
      | Delphine          |
      | Moriah.Stanton    |
    When I get userId for the user with name "<user_name>"
    Then I check that comment for the user with saved Id contain valid emails
    Examples:
      | user_name |
      | Bret      |

  @api @withContext
  Scenario Outline: Users data validation (albums and header)
    When I get userId for the user with name "<user_name>" and check the response status code is 200
    Then I check that albums for the user with saved Id contain titles
    When I check that "server" header value from the response is "cloudflare"
    Examples:
      | user_name |
      | Samantha  |

