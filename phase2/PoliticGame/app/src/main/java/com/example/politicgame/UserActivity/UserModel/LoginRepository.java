package com.example.politicgame.UserActivity.UserModel;

import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.UserActivity.UserDatabase.LoginDatabaseHandler;

/**
 * Class that requests authentication and user information from the remote data source and maintains
 * an in-memory cache of login status and user credentials information.
 */
class LoginRepository {

  private static volatile LoginRepository instance;
  private LoginDatabaseHandler dataSource;
  private UserAccount user = null;
  // private constructor : singleton access
  private LoginRepository(LoginDatabaseHandler dataSource) {
    this.dataSource = dataSource;
  }

  static LoginRepository getInstance(LoginDatabaseHandler dataSource) {
    if (instance == null) {
      instance = new LoginRepository(dataSource);
    }
    return instance;
  }

  private void setLoggedInUser(UserAccount user) {
    this.user = user;
  }

  public Result login(String username, String password) {
    Result result = dataSource.connectDatabase(username, password);
    if (result instanceof Result.Success) {
      setLoggedInUser(((Result.Success) result).getData());
    }
    return result;
  }
    }

