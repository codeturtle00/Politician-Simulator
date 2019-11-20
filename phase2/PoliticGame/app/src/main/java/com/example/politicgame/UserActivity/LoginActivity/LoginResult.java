package com.example.politicgame.UserActivity.LoginActivity;

import androidx.annotation.Nullable;

/** Authentication result : success (user details) or error message. */
class LoginResult {
  @Nullable private LoggedInUserView success;
  @Nullable private Integer error;
  @Nullable private Integer nullUser;

  LoginResult(@Nullable Integer error) {
    this.error = error;
  }

  LoginResult(@Nullable Integer nullError, boolean nullUser) {
    if (nullUser) {
      this.nullUser = nullError;
    }
    else {
      this.error = nullError;
    }
    }

  LoginResult(@Nullable LoggedInUserView success) {
    this.success = success;
  }

  @Nullable
  LoggedInUserView getSuccess() {
    return success;
  }

  @Nullable
  Integer getError() {
    return error;
  }

  Integer getNull() {
    return nullUser;
  }
}
