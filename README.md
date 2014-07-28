Githublo
========

Githublo is a small tool for synchronizing [Github](https://github.com/) issues with [Trello](https://trello.com) cards.

Usage
-----

First you need an API key for accessing the Trello API. Log in Trello and visit a link:
https://trello.com/1/appKey/generate. Copy the "key" field from this page.

After that you should give the Githublo permission to add cards to your boards (that's what you're after, aren't you?)
Prepare the link
https://trello.com/1/authorize?key={your_API_key}&name=Githublo&expiration=never&response_type=token&scope=read,write
and visit it in browser. Copy the security token from this page.

Now you want to know identifier of your board. Visit the page
https://api.trello.com/1/organizations/{your_organization}/boards?key={your_API_key} and get identifier from there.

And you need the list to that Githublo will write your Github issues. Get it here:
https://api.trello.com/1/boards/{your_board_id}/lists?key={your_API_key}

Now simply run the tool and pass all the information as command line arguments:

    $ sbt 'run github-user github-repository trello-board-id trello-list-id trello-api-key trello-api-token' 

Dependencies
------------

Install [fork of trello4j](https://github.com/johanmynhardt/trello4j) to local Maven repository:

    $ git clone https://github.com/johanmynhardt/trello4j
    $ cd trello4j
    $ mvn install -DskipTests
