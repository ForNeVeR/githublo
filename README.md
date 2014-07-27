Githublo
========

## Dependencies

Install [fork of trello4j](https://github.com/johanmynhardt/trello4j) to local Maven repository
(`mvn install -DskipTests`).

## FAQ

*Q:* How to get Trello board id?

*A:* See here: https://api.trello.com/1/organizations/{your_organization}/boards?key={your_API_key}

*Q:* How to get Trello list id?

*A:* See here: https://api.trello.com/1/boards/{your_board_id}/lists?key={your_API_key}

*Q:* How to get permission?
*A:* https://trello.com/1/authorize?key={your_API_key}&name=Githublo&expiration=never&response_type=token&scope=read,write

It will grant you an API token.