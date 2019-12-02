package com.example.politicgame.CharacterSelect;

import android.util.Log;

import com.example.politicgame.Character.UserTools.UserAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

class CellFinder {
    private UserAccount userAcc;

    CellFinder(UserAccount userAcc){
        this.userAcc = userAcc;
    }


    /**
     * Returns an array of instances of CellInfo that represent the information to be shown in the
     * cells in the activity
     *
     * @return          An array of CellInfo objects containing information on what to show in the
     */
    CellInfo[] getCellInfo() {

        JSONObject charCell = getExistingCharacters();

        Log.i("Read charCell", "THE JSON OUTPUT IS HERE");
        Log.i("Read charCell", charCell.toString());

        int currCell = 0;
        CellInfo[] cellData = new CellInfo[2];
        String[] cellNames = new String[] {"", ""};


        Iterator<String> keys = charCell.keys();
        String currKey;
        String[] msg = new String[2];

        try {
            while (keys.hasNext()) {
                msg[currCell] = "";
                currKey = keys.next();

                msg[currCell] += "Character Name: " + currKey + ".\n";

                JSONArray electionResults = charCell.getJSONObject(currKey).getJSONArray("SCORE");

                msg[currCell] += "Completed elections: " + electionResults.length() + ".\n";

                int electionsWon = 0;
                for (int i = 0; i < electionResults.length(); i++) {
                    if (electionResults.getInt(i) > 200) {
                        electionsWon += 1;
                    }
                }

                msg[currCell] += "Elections won: " + electionsWon + ".";

                cellNames[currCell] = currKey;

                currCell++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (currCell == 2) {
            cellData[0] = new LoadedCell(msg[0], cellNames[0]);
            cellData[1] = new LoadedCell(msg[1], cellNames[1]);
        } else if (currCell == 1) {
            cellData[0] = new LoadedCell(msg[0], cellNames[0]);
            cellData[1] = new EmptyCell();
        } else {
            cellData[0] = new EmptyCell();
            cellData[1] = new EmptyCell();
        }

        return cellData;
    }

    /**
     * Returns a JSONObject of the existing characters
     *
     * @return  A JSONObject of the existing characters
     */
    private JSONObject getExistingCharacters() {
        JSONArray charArray = userAcc.getCharArray();
        JSONObject returnChar = new JSONObject();

        Log.i("Get existing characters", charArray.toString());

        try {
            int i;
            for (i = 0; i < charArray.length(); i++) {
                JSONObject charObject = charArray.getJSONObject(i);
                String charName = charObject.keys().next();
                returnChar.put(charName, charObject.get(charName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return returnChar;
    }
}
