package com.example.politicgame.LoadCharacter;

import android.util.Log;

import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.Character.UserTools.UserAccount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class FillCellFacade {

    private UserAccount userAcc;
    private CellFinder cf;

    public FillCellFacade(UserAccount userAcc){
        this.userAcc = userAcc;
        cf = new CellFinder(this.userAcc);
    }

    /**Returns a CellInfo array which contains instances of classes that implement CellInfo. The
     * elements of the array can then be used to extract information about the user's characters
     *
     * NOTE:    This remains inside our facade so that we can easily implement new features in the
     *          LoadCharacterActivity.java class. Since all we need to do is implement the new line
     *          here and implement the functionality inside the CellInfo implemented classes so that
     *          the user doesn't worry about the design. So it wouldn't be strange for the
     *
     * @return A CellInfo array containing the information for each cell
     */
    public CellInfo[] getCells(){
        JSONObject charCell = cf.getExistingCharacters(); // Get this here to later parse for more info
        CellInfo[] returnCells = cf.getCellInfo(charCell);

        return returnCells;
    }
}
