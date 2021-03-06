Githublo [![ForNeVeR/githublo](http://issuestats.com/github/ForNeVeR/githublo/badge/pr?style=flat-square)](http://www.issuestats.com/github/ForNeVeR/githublo) [![ForNeVeR/githublo](http://issuestats.com/github/ForNeVeR/githublo/badge/issue?style=flat-square)](http://www.issuestats.com/github/ForNeVeR/githublo) [![BuildStatus](https://travis-ci.org/ForNeVeR/githublo.png?branch=develop)](https://travis-ci.org/ForNeVeR/githublo)
========

Githublo is a small tool for synchronizing [Github](https://github.com/) issues with [Trello](https://trello.com) cards.

Usage
-----

First you need an API key for accessing the Trello API. Log in Trello and visit a link:
https://trello.com/1/appKey/generate. Copy the "key" field from this page.

After that you should give the Githublo permission to add cards to your boards (that's what you're after, aren't you?)
Prepare the link
`https://trello.com/1/authorize?key={your_API_key}&name=Githublo&expiration=never&response_type=token&scope=read,write`
and visit it in browser. Copy the security token from this page.

Now you want to know identifier of your board. Visit the page
`https://api.trello.com/1/members/{your_user_name}/boards?key={your_API_key}` (or
`https://api.trello.com/1/organizations/{your_organization}/boards?key={your_API_key}`) and get identifier from there.
For private boards you need to add API token (e.g.
`https://api.trello.com/1/members/{your_user_name}/boards?key={your_API_key}&token={tour_API_token}`).

And you need the identifier of a list to that Githublo will write your Github issues. Get it here:
`https://api.trello.com/1/boards/{your_board_id}/lists?key={your_API_key}`.

Now simply run the tool and pass all the information as command line arguments:

    $ sbt 'run github-user github-repository trello-board-id trello-list-id trello-api-key trello-api-token'

Currently Githublo is limited to 5 operations per run so it won't ruin all your Trello board.
