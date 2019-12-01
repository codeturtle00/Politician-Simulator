package com.example.politicgame.UserActivity.UserDatabase;

import android.content.Context;
import com.example.politicgame.Character.UserTools.UserAccount;
import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/** This class is used for saving registration data into database
 * If registration data is saved to database successfully, return
 * If registration data is not saved to database , return */
public class RegistrationDatabaseHandler {
    private Context context;
    private FileSavingService fileSaving;
    private static final String FILE_NAME = "userLogin.json";
    public RegistrationDatabaseHandler(Context context) {
        this.context = context;
        this.fileSaving  = new FileSavingService(context);
    }
    /** Precondition: Username and password passed into this class is clean and valid
     * as it go through FormState
     */
    public Result register(String userName, String password){
        if (isDuplicate(userName)){
            return new Result.InvalidResult.DuplicateResult(userName);
        }
        else {
            try{
            saveUserToDb(userName, password);
            UserAccount user = new UserAccount(userName, context);
            return new Result.Success(user);}
            catch(Exception e){
                return new Result.Error(e);
            }
        }
    }
    /** Check if the the username is duplicated in the database,return true if found the user in the database ,return false otherwise*/
    private boolean isDuplicate(String username) {
        JSONArray jArray = this.fileSaving.readJsonFile(FILE_NAME);
        try {
            for (int i = 0; i < jArray.length(); i++) {
                if (jArray.getJSONObject(i).getString("UserName").equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    /** Save username along with password into the database
     * Precondition: the userName and password passed into this method is
     * a.valid(i.e. not empty and follow the validation of username and database from formState
     * b.not duplicate(i.e.return false from isDuplicated method*/
    private void saveUserToDb(String userName, String password){
        JSONObject userObject = new JSONObject();
        try {
            userObject.put("UserName", userName);
            userObject.put("Password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fileSaving.appendJsonObject(userObject, FILE_NAME);
    }
}