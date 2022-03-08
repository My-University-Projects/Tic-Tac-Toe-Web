# Tic-Tac-Toe-Web
## Simple Tic Tac Toe web game created in MVC model.
What technologies i have used:
- Spring Boot framework
- PostgreSQL for database system
    In database are 2 tables for each game:
    1st row with the information about players and game
    2nd row with the moves history of the players
    Information about games from database are shown in screenshots below
- Web Sockets for communicating each players from different browsers during the game
- Bootstrap library in frontend part

As a player we have few options:
- Create own game with the game id and wait for other players to join 
- Join to random game without two players already
- Join to concrete game by id of the game

## Since I don't have much to tell about the game itself and its logic, I present screenshots from the game below

Main menu of the game - there player can create own game or join to already created game
![image](https://user-images.githubusercontent.com/93645494/157257779-30896523-7a2c-4d5d-9df7-873d42a30732.png)

Info when player has created game
![image](https://user-images.githubusercontent.com/93645494/157257977-c2b3232f-5ae9-4c20-b1b7-d94a2a09c995.png)

When second player joins to the game
![image](https://user-images.githubusercontent.com/93645494/157258042-25625bc0-c6ad-4186-918f-e706402b21e9.png)

Screen of the game
![image](https://user-images.githubusercontent.com/93645494/157258139-ed340bbf-8bcd-479c-bfe8-305c7e1f7975.png)

Information when the game is finished
![image](https://user-images.githubusercontent.com/93645494/157258200-06e987cf-6c39-4da2-b3e4-3515615a753b.png)

Table on the actual games page(We can see there only games with statues STARTED and IN_PROGRESS, games with status FINISHED are unnecessary)
![image](https://user-images.githubusercontent.com/93645494/157258437-2dda1cff-3171-416c-bb3f-5673182341ab.png)




