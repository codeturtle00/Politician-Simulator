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

public abstract class BoardLoader implements LeaderBoard{
    final String FILE_NAME = "user.json";
    FileSavingService fileSaving;

    BoardLoader(Context lastContext){
        this.fileSaving = new FileSavingService(lastContext);
    }

    List<JSONObject> sortBoard(JSONArray charScores){

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

    abstract JSONArray getScores();

    public List<JSONObject> getBoard(){
        JSONArray charScores = getScores();
        List<JSONObject> boardItems = sortBoard(charScores);
        return boardItems;
    }
}
