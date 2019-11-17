The Politic Game

We have made a game consisting of 3 levels.
 
###User and Login(Registration)System
A user is required to log in (or register if they don’t have an account yet) to start the app.
All the accounts are saved in user_login.json in local.
At least one character is required for each user to play the game.
For new users,they are required to select their customized characters as soon as they get registered. 
For logged in users, they can either create a new character or choose their previous characters.
All the characters are saved in user.json in local.

###Scoring system
Each level will have its own scoring system. Full score will be 100 for each level.
Notice that we will not update the user score into the database until user pass the level.
Then if the user quit in the middle of the app, the user will be brought back to the start of the latest level 
they were playing on after they go through the log in process again, and choose the character they were playing
 as with initial score for each level.Once the user finishes one playthrough, the score of each level will be 
displayed and a total score will be recorded on the leaderboard. User can choose to replay the character if they wish.

###Customization
We have 3 different customizations. 
The user can choose one of three soundtracks that will be played throughout the game.
 We can choose red or blue for the background theme. 
And we can choose one of two characters as our politicians throughout the 3 levels.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system.

### Gradle build

The minimum SDK version must be at least 19 but at most will support 29. Our goal is to support the Pixel 3 API 28

### Playing the games
Game 1
Game 1 is played using many quick touch and swipe gestures, making it quite difficult to do well in when using a mouse or a trackpad.
 For best playability, game 1 should be played on a touchscreen.
