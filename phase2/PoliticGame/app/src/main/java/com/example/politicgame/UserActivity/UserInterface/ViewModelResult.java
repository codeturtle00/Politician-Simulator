package com.example.politicgame.UserActivity.UserInterface;
import androidx.annotation.Nullable;
import com.example.politicgame.UserActivity.UserModel.LoggedInUserView;
/** Authentication result : success (user details) or error or invalid message. */
public class ViewModelResult {
  @Nullable private LoggedInUserView success;
  @Nullable private Integer error;
  @Nullable private String invalidMessage;
  public ViewModelResult(@Nullable Integer error) {this.error = error; }
  public ViewModelResult(@Nullable String invalidMessage) { this.invalidMessage = invalidMessage; }
  public ViewModelResult(@Nullable LoggedInUserView success) {this.success = success;}
  @Nullable
  public LoggedInUserView getSuccess() { return success; }
  @Nullable
  public Integer getError() { return error ;}
  @Nullable
  public String getInvalid() {
    return invalidMessage;
  }
}
