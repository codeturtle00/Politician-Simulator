package com.example.politicgame.Leaderboard;

import android.content.Context;
import android.util.Log;

import com.example.politicgame.Common.FileSavingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BoardLoader implements LeaderBoard {
  final String FILE_NAME = "user.json";
  FileSavingService fileSaving;

  BoardLoader(Context lastContext) {
    this.fileSaving = new FileSavingService(lastContext);
  }

  /**
   * Sort the JSONArray given so that the function returns a list of JSONObjects representing the
   * top 3 characters in their game mode.
   *
   * <p>NOTE: The structure of each index of the JSONArray must be:
   *
   * <p>{ <Character name>: { score : <Score>, userName : <User name> } }
   *
   * @param charScores The JSONArray with JSONObjects elements structured in the above format
   * @return A list of the top 3 characters in JSONObjects with the above format
   */
  List<JSONObject> sortBoard(JSONArray charScores) {

    JSONObject first = new JSONObject();
    JSONObject second = new JSONObject();
    JSONObject third = new JSONObject();
    try {
      first.put("fill", new JSONObject().put("score", 0));
      second.put("fill", new JSONObject().put("score", 0));
      third.put("fill", new JSONObject().put("score", 0));

      for (int i = 0; i < charScores.length(); i++) {
        JSONObject charInfo = charScores.getJSONObject(i);
        String charName = charInfo.keys().next();
        int charScoreNum = charInfo.getJSONObject(charName).getInt("score");

        Log.i("First", first.toString());
        Log.i("Second", second.toString());
        Log.i("Third", third.toString());
        Log.i("charScores(name)", charInfo.toString());

        if (charScoreNum > first.getJSONObject((first.keys().next())).getInt("score")) {
          third = second;
          second = first;
          first = new JSONObject().put(charName, charInfo.getJSONObject(charName));
        } else if (charScoreNum > second.getJSONObject((second.keys().next())).getInt("score")) {
          third = second;
          second = new JSONObject().put(charName, charInfo.getJSONObject(charName));
        } else if (charScoreNum > third.getJSONObject((third.keys().next())).getInt("score")) {
          third = new JSONObject().put(charName, charInfo.getJSONObject(charName));
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    List<JSONObject> orderedBoard = new ArrayList<>(Arrays.asList(first, second, third));
    return orderedBoard;
  }

    /**
     * Return a JSONArray with the structure below from the JSON files
     *
     * NOTE: The structure of each index of the JSONArray must be:
     *
     *          {
     *              <Character name> :
     *              {
     *                  score : <Score>,
     *                  userName : <User name>
     *              }
     *          }
     *
     * @return  A JSONArray of all the characters and their scores in JSONObjects in each index in
     *          the above format
     */
  abstract JSONArray getScores();

    /**
     * Returns a list of the top 3 scores and the users and characters associated with each score in
     * JSONObjects
     *
     * @return  A list of JSONObjects containing the top 3 scores and their respective users and
     *          characters
     */
  public List<JSONObject> getBoard() {
    JSONArray charScores = getScores();
    List<JSONObject> boardItems = sortBoard(charScores);
    return boardItems;
  }
}
