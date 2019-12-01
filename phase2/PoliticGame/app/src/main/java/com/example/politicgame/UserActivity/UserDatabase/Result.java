package com.example.politicgame.UserActivity.UserDatabase;

import com.example.politicgame.Character.UserTools.UserAccount;

/**
 * A class that holds the result of processing the information to the database
 * And it is sent to ViewModel for further processes,used as an indication for
 * different action in ViewModel
 * This class contains three inner static classes:
 * Success,Error,EmptyResult
 */
public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        } else if (this instanceof Result.InvalidResult.EmptyResult) {
            Result.InvalidResult.EmptyResult empty =(Result.InvalidResult.EmptyResult) this;
            return "Empty User[Can not found =" + empty.getLostData() +"]";
        } else if (this instanceof Result.InvalidResult.DuplicateResult) {
            Result.InvalidResult.DuplicateResult duplicate =(Result.InvalidResult.DuplicateResult) this;
            return "Empty User[Duplicate =" + duplicate.getLostData() +"]";
        }
        return "";
    }

    /** Success sub-class
    Return an instance of Success to ViewModel if the process to database is successful,including the
     following use cases:

     */
    public static class Success extends Result {
        private UserAccount data;
        public Success(UserAccount data) { this.data = data; }
        public UserAccount getData() { return this.data; }
    }

    /**Error sub-class
     Return an instance of Error to ViewModel if the process to database fails in the following ways:
     a.Access deny(Wrong UserName and password for login)

    */
    public static class Error extends Result {
        private Exception error;
        public Error(Exception error) {this.error = error;}
        public Exception getError() {return this.error;}
    }

    /**Error sub-class
     Return an instance of Error to ViewModel if the process to database fails in the following ways:
     a.Empty Result(no user was found from database during login process)
     b.Duplicate Result(a user already exists in the database during registration process)
     */
    public static class InvalidResult extends Result {
        private String invalidData;
        public InvalidResult(String invalidData) { this.invalidData = invalidData;}
        public String getLostData() { return this.invalidData;}
        public static class EmptyResult extends InvalidResult{
            public EmptyResult(String emptyUserName){
                super(emptyUserName);
            }
        }
        public static class DuplicateResult extends InvalidResult{
            public DuplicateResult(String duplicateUserName){
                super(duplicateUserName);
            }
        }

    }
}
