# RESTful API RSS aggregator server using Go  
### This project is a RESTful API built with Go, allowing users to manage and track RSS (Really Simple Syndication) feeds, designed for personal content aggregation like blogs, news sites, and podcasts.  

### Features:  
- Creating users `/users` and feeds `/feeds` with the ability for users to follow specific feeds via `/feedFollows`. All through `Post` method.
- Retrieving feed follows `/feedFollows` for each user using `Get` method with authentication.
- Allowing users to Delete specific feed follows via `/feedsFollows/{feedFollowID}` through `Delete` method with authentication.
- authentication using Api keys.
- Retrieving feeds using `Get` method.
- Creating posts through scraping feeds.
- Retrieving posts `/posts` with authentication using `Get` method.

All routes are mounted on `/v1` route  

### Used techs:
- GoLang
- Sqlc
- Goose
- PostgreSQL  