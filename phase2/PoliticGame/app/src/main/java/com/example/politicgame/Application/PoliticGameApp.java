package com.example.politicgame.Application;

import android.app.Application;

import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.Games.SpeechGame.SpeechGameViewModel;

public class PoliticGameApp extends Application {
  // Current Account
  private UserAccount currentUser;
  private String currentCharacter;

  // Thematic selection
  private ThemeLoader themeHandler;

  // Song selections
  private MusicPlayer musicHandler;

  public SpeechGameViewModel getSpeechView() {
    return speechView;
  }
  private SpeechGameViewModel speechView = new SpeechGameViewModel();


  public void onCreate() {
    super.onCreate();

    // Theme selector
    themeHandler = new ThemeLoader(this);

    // Music Player
    musicHandler = new MusicPlayer(this);

    speechView.loadQuestions();
  }



  // Methods for tracking current user

  /**
   * Returns the current UserAccount instance
   *
   * @return The current UserAccount instance
   */
  public UserAccount getCurrentUser() {
    return currentUser;
  }

  /**
   * Sets current UserAccount
   *
   * @param newUser The new UserAccount to set
   */
  public void setCurrentUser(UserAccount newUser) {
    this.currentUser = newUser;
  }

  /**
   * Returns the current character name
   *
   * @return The current character name
   */
  public String getCurrentCharacter() {
    return currentCharacter;
  }

  /**
   * Sets the current character name
   *
   * @param newCharacter The name of the current character
   */
  public void setCurrentCharacter(String newCharacter) {
    this.currentCharacter = newCharacter;
  }



  // Methods for theme selection

  /**
   * Choose the new theme
   *
   * @param isBlue Choose if the current theme is blue
   */
  public void chooseBlueTheme(boolean isBlue) { themeHandler.chooseBlueTheme(isBlue); }

  /**
   * Returns if the current theme is blue or not
   *
   * @return Is the theme blue?
   */
  public boolean isThemeBlue() { return themeHandler.isThemeBlue(); }



  // Methods for music selection

  /**Switches the music track*/
  public void switchMusic() { musicHandler.switchMusic(); }

  /**Returns the current track number
   *
   * @return The current track number
   */
  public int getCurrentTrackNum() {
    return musicHandler.getCurrentTrackNum();
  }

  /**Returns if the music is playing
   *
   *
   * @return Is the music playing
   */
  public boolean isMusicOn() { return musicHandler.getMusicOn(); }

  /**Toggles the music player*/
  public void toggleMusic() { musicHandler.toggleMusic(); }
}
