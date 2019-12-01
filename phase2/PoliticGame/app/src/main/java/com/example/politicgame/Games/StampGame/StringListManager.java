package com.example.politicgame.Games.StampGame;

import android.content.Context;

import com.example.politicgame.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The StringListManager contains several lists of strings that are used by the WordListBuilder
 * class to construct WordList
 */
class StringListManager {
  private Context context;

  StringListManager(Context context) {
    this.context = context;
  }


  List<String> getPosVerbStringList() {
    return new ArrayList<>(
            Arrays.asList(
                    this.context.getString(R.string.pos_verb_campaign),
                    this.context.getString(R.string.pos_verb_aid),
                    this.context.getString(R.string.pos_verb_donate),
                    this.context.getString(R.string.pos_verb_gift)));
  }

  List<String> getNegVerbStringList() {
    return new ArrayList<>(
            Arrays.asList(
                    this.context.getString(R.string.neg_verb_sendPolice),
                    this.context.getString(R.string.neg_verb_punch),
                    this.context.getString(R.string.neg_verb_launchNuke),
                    this.context.getString(R.string.neg_verb_sendArmy),
                    this.context.getString(R.string.neg_verb_launchInvest),
                    this.context.getString(R.string.neg_verb_laugh)));

  }

  List<String> getPosNotCNounStringList() {
    return new ArrayList<>(
            Arrays.asList(
                    this.context.getString(R.string.pos_NotC_Noun_BillRye),
                    this.context.getString(R.string.pos_NotC_Noun_Gandhi),
                    this.context.getString(R.string.pos_NotC_Noun_Jacki),
                    this.context.getString(R.string.pos_NotC_Noun_JohnOlive),
                    this.context.getString(R.string.pos_NotC_Noun_ToeKnee)));
  }

  List<String> getNegNotCNounStringList() {
    return new ArrayList<>(
            Arrays.asList(
                    this.context.getString(R.string.neg_NotC_Noun_leaderKorea),
                    this.context.getString(R.string.neg_NotC_Noun_Colin),
                    this.context.getString(R.string.neg_NotC_Noun_Kavin)));
  }

  List<String> getPosCNounStringList() {
    return new ArrayList<>(
            Arrays.asList(
                    this.context.getString(R.string.pos_C_Noun_Puppy),
                    this.context.getString(R.string.pos_C_Noun_Peacock),
                    this.context.getString(R.string.pos_C_Noun_students)));
  }

  List<String> getNegCNounStringList() {
    return new ArrayList<>(
            Arrays.asList(
                    this.context.getString(R.string.neg_C_Noun_sealClubber),
                    this.context.getString(R.string.neg_C_Noun_thieves)));
  }

}
