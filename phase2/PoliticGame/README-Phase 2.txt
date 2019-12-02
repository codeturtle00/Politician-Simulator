# CSC207 Phase 2 Project - Politician Simulator

NOTE FOR READABILITY THIS IS BEST READ IN A DOCUMENT VIEWER LIKE WORD
NOTE FOR READABILITY THIS IS BEST READ IN A DOCUMENT VIEWER LIKE WORD
NOTE FOR READABILITY THIS IS BEST READ IN A DOCUMENT VIEWER LIKE WORD
NOTE FOR READABILITY THIS IS BEST READ IN A DOCUMENT VIEWER LIKE WORD
NOTE FOR READABILITY THIS IS BEST READ IN A DOCUMENT VIEWER LIKE WORD
NOTE FOR READABILITY THIS IS BEST READ IN A DOCUMENT VIEWER LIKE WORD
NOTE FOR READABILITY THIS IS BEST READ IN A DOCUMENT VIEWER LIKE WORD

This is phase 2 of our final project of CSC207H1. Our final project is an app that contains three games that can be played separately or together as one game. We have implemented features such as login/registration, different game modes and a leaderboard that tracks the multiple game modes, different unlockable characters, a file saving system for each user, pause menus, theme customization and music players with different tracks.


## Phase 1 Design Overview

During phase 1, we only had the three games which were played sequentially, the login/registration, the leaderboard which at the time only tracked one game mode, two characters, a file saving system for each user, the pause menu, and the music player. At the time, our three statistics were the total amount of games played, games won and also a list of scores a specific character had achieved for their playthroughs.

## Phase 2 Improvement Overview

During phase 1, we had complaints that the statistics were not obtainable because of one of the games being too difficult to play. We also had issues with UI being misaligned and lacked polish in certain areas. On top of that, many of our back-end classes were overly large due to time constraints and not all our classes were properly encapsulated.

We improved on all of that during our phase 2.

### Improvement 1: Playability

First we improved playability and the quality of each game to ensure it would be played properly so that there would be no issues regarding the inability to gather statistics. The first game, BabyGame, saw the biggest changes with many updates that ensured the game would be stable and fun.

### Improvement 2: UI Redesign
We also took the time to redo the UI for all the buttons in the game and changed a lot of the visuals for different features of the game. We created styles for our buttons and special backgrounds for different screens. On top of that we changed our main menu into one activity instead of the two we had in phase 1 and we created popup menus for login, registering, settings and the pause menu.

### Improvement 3: More encapsulation, Less code smells
To break down our classes and improve encapsulation, we realized many of our classes should be divided into other classes and could break down the implementation through design principles. We broke down classes like LoadCharacterActivity.java into a class that only handled the front end and created facades and strategy design to handle the logic and the in-betweens that interacted with the database. On top of that, we also realized that we could better use packaging to improve the security of encapsulation methods. For example, we realized that our implementation of the leaderboard could be packaged by components so that no other packages have access to the logic for leaderboard.

## Phase 2 Additions

To fulfill the conditions of Phase 2, we had to extend the code to see how extensible it was. To do that we implemented three things: new game modes, a multi-feature leaderboard and unlockable characters.

### Addition 1: New Game Modes

We added new game modes so that you could also play each game individually as well as sequentially in a playthrough of all three games. There is now a new screen that shows up once you decide to start a new game, this screen allows you to choose between the four game modes: Election, BabyGame, SpeechGame, and StampGame. The Election game mode (or Arcade as we call some of its class attributes) is essentially what we had for phase 1 and it has the same end screen and features as before, which means you can delete current playthroughs or continue if you left the game mid-way. If you select any of the other three new game modes, you will be led to a different results screen at the end of the individual game.

### Addition 2: New Leaderboard Design

We also added onto our pre-existing leaderboard by allowing it to also show high scores for each of these game modes, allowing for 4 different leaderboards to exist, as well as allowing the user to decide if they wanted to save or erase their score on completion of their game. No matter which user or character makes the high score, they will appear on the leaderboard so as long as the character has not been deleted and are among the top 3 scores of the specific game mode. But if you score a high score for any of the games in an Election game mode, the score is not meant to show up in the leaderboard for the individual game. It is intended that you only have a high score if you obtained it through that mode.

### Addition 3: New Character Selection / Unlockable Characters

Our last addition was the implementation of more selectable characters in our character creation screen, bringing two selectable characters to five. We initially had two characters, who are still the first two characters. The three new characters we added are locked behind a score limit, which is unlocked when the user achieves a total score that is greater than the unlock limit. For example, once the total of the user’s saved scores is 100 or over, they unlock the 3rd character, and this continues to the 5th character who is unlocked at 300 points. The value of total saved scores comes from all game modes and is incremented so as long as the user decides to save the scores after the game, and this total persists even if the user deletes the character who made the score.

### Addition 4: More Design Patterns (Singleton, Builder, Strategy, Observer)

To fix a lot of the issues our group had with large classes and encapsulation we brushed up on SOLID and design patterns to improve on our code. There are a couple interesting design patterns we used that are of note.

The design pattern we relied on the most was the Singleton pattern which we applied through the PoliticGameApp.java class. It is one of the famous “Gang of Four” design patterns and we use it to keep track of variables across the application. Its main use is to keep track of the current user through a class we created called UserAccount. This allows for quick access to the variable and subverts the need to pass information about the user through intents which requires objects like UserAccount to also implement Serializable which is a complication as UserAccount needs to keep track of objects that are non-Serializable, leading to possible NonSerializableExceptions.

The strategy design pattern also saw frequent use throughout phase 2, simply because it made it easy to implement different game modes for each of the games. We used the design pattern by creating a GameMode interface which would have two public methods, save() and next(). Objects that implemented GameMode would be passed to each game and would call save() and next() which would save and start new intents differently based on which implementation of GameMode was passed on. This made moving in and out of games for different game modes simpler and made changes to individual implementations easy to make. This was also done for the leaderboard which equivalently had the LeaderBoard interface to differentiate between different types of leaderboards.

Apart from the two listed above, we used dependency injection, facade, factories, observers, etc. in numerous other classes. They aren’t as interesting in our case, but we used them to remove dependencies, break down classes and improve encapsulation.

## Additional Instructions For Games And Diagrams

### BabyGame

This game has you swipe and tap to simulate cradling, kissing and tickling in order to please the baby. If any two-way arrows appear, swipe back and forth in the directions of the arrows in order to please the baby. If lips or hands appear, then tap them to continue pleasing the baby.

### SpeechGame

In this game you are having a speech and you must stay on topic with the prompt at hand. You will be given several prompts and a list of topics you can talk about. Pick the one that is most relevant and you will increase your score, answer incorrectly and you will lose points.

### StampGame

In this game you are given randomized prompts from lists of nouns, verbs, etc. As the leader of your party, it is your job to decide what policies your party will support. You can say yes or no to the prompts, but your supporters may have adverse reactions to some of the things you may support so use your best judgement to decide what is sensible.

### UML Diagrams

There is a file directory called UML Diagrams under the PoliticGameApp file. The UML diagrams inside were made through Violet (http://alexdp.free.fr/violetumleditor/page.php) and are in HTML format. Open the .html files in the browser to view them.

## Comments on Resources

Some of the resources for the app are our own and some are from the internet and therefore may be copyrighted. We don’t have any plans to deploy the app on the app store so we are using these resources to best show off the project to an audience of TA’s.
