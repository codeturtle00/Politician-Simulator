package com.example.politicgame.UserActivity.UserDatabase;

import com.example.politicgame.Character.UserTools.UserAccount;

/**
 * Class that requests authentication and user information from the remote data source and maintains
 * an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

  private static volatile LoginRepository instance;
  private LoginDatabaseHandler dataSource;
  private UserAccount user = null;
  // private constructor : singleton access
  private LoginRepository(LoginDatabaseHandler dataSource) {
    this.dataSource = dataSource;
  }

  public static LoginRepository getInstance(LoginDatabaseHandler dataSource) {
    if (instance == null) {
      instance = new LoginRepository(dataSource);
    }
    return instance;
  }

  private void setLoggedInUser(UserAccount user) {
    this.user = user;
  }

  public Result login(String username, String password) {
    Result result = dataSource.login(username, password);
    if (result instanceof Result.Success) {
      setLoggedInUser(((Result.Success) result).getData());
    }
    return result;
  }
    }

