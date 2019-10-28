package com.example.politicgame.SpeechGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.politicgame.MainActivity;
import com.example.politicgame.PauseActivity;
import com.example.politicgame.R;
import com.example.politicgame.StampGame.StampInstructionActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Set;

public class SpeechActivity extends AppCompatActivity {
  private SpeechGame speech;
  private static final String FILE_NAME = "SpeechPrompts.txt";
  private static final String TAG = "Speech Activity";
  public static final String INPUT_MESSAGE = "politicgame.speech.input";
  public static final String CORRECTION_MESSAGE = "politicgame.speech.result";
  private final int POINTSGIVEN = 1;
  private String correct;

  private void setSpeech(SpeechGame speech) {
    this.speech = speech;
    initFile();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_speech);

    final Button pauseB = findViewById(R.id.pause);
    pauseB.setOnClickListener(
            new View.OnClickListener() {
              public void onClick(View v) {
                Log.i("Button", "The pause button has been clicked");

                //The method below will pause the game and handle the following inputs
                openPauseMenu();
              }
            });
  }

  @Override
  protected void onStart() {
    super.onStart();
    this.setSpeech(new SpeechGame());
    // A button that will switch to next page if user clicks
    final Button button = findViewById(R.id.speechNext);
    button.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            openStampGame();
          }
        });
    // TextView for prompt and choices
    TextView prompt = findViewById(R.id.prompt);
    TextView choiceA = findViewById(R.id.ChoiceA);
    TextView choiceB = findViewById(R.id.ChoiceB);
    TextView choiceC = findViewById(R.id.ChoiceC);
    TextView choiceD = findViewById(R.id.ChoiceD);
    Set<String> speechData = this.speech.getDisplay().keySet();
    String promptKey = randomSelect(speechData);
    prompt.setText(promptKey);
    String choices = this.speech.getDisplay().get(promptKey);
    String[] result = choices.split(",");
    TextView[] textViews = {choiceA, choiceB, choiceC, choiceD};
    for (int i = 0; i < 4; i++) {
      if (!result[i].contains("*")) {
        this.correct = result[i].trim();
        textViews[i].setText(result[i].trim());
      } else {
        textViews[i].setText(result[i].substring(1).trim());
      }
    }
  }

  private String randomSelect(Set<String> speechData) {
    Random r = new Random();
    int num = r.nextInt(speechData.size());
    return (String) speechData.toArray()[num];
  }

  /*Initialize the questions in txt file
   * Save Questions and answers in SpeechPrompts.txt*/
  private void initFile() {
    String textToSave =
        "The country needs more budget to spend on improving public education and there is discussion for raising the money\n"
            + "taxes,*environment,*religion,*military\n"
            + "Many young people in the country are struggling to pay back student loans and there is discussion for the government to relieve this debt\n"
            + "*immigration,*healthcare,*corporations,taxes\n"
            + "Climate change is rising threat to the planet and there is discussion to expand on the country's renewable energy sources\n"
            + "energy,*economy,*unions,*trade\n"
            + "The homelessness rate in the country is rising each year and there is discussion to introduce a universal basic income\n"
            + "*immigration,poverty,*internet,*security\n"
            + "Surveys show that many citizens do not like their governors on the provincial and municipal level and there is discussion to introduce a term limit\n"
            + "voting,*education,*taxes,*medication\n"
            + "A recent trend shows that many corporations are outsourcing jobs to other parts of the world and there is discussion so keep jobs domestic\n"
            + "*diversity,*religion,*healthcare,economy\n"
            + "Genetically modified foods is a controversial topic and there is discussion to heavily regulate them or ban them\n"
            + "*energy,*corporations,agriculture,*voting\n"
            + "New strains of diseases are on the rise and there is discussion for the government to introduce mandatory vaccinations\n"
            + "*trade,*military,*education,healthcare\n"
            + "Studies have shown that a 4 day work week increases productivity and there is discussion for this change to be implemented\n"
            + "*security,corporations,*immigration,*poverty\n"
            + "Older citizens are struggling to retire at reasonable age and there is discussion to increase senior assissance\n"
            + "pension,*internet,*military,*diversity";

    FileOutputStream outputStream;
    File rootDir = getFilesDir();
    Log.i(TAG, "root directory is " + rootDir);
    try {
      outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
      outputStream.write(textToSave.getBytes());
      outputStream.close();
      readFile();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /* Set the prompt and choices into speech attribute*/
  private void readFile() {
    try {
      FileInputStream fileInputStream = openFileInput(FILE_NAME);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String lines;
      StringBuilder choiceBuilder;
      StringBuilder promptBuilder = new StringBuilder();
      int lineNum = 0;
      while ((lines = bufferedReader.readLine()) != null) {
        // even lines are storing the prompt
        if (lineNum % 2 == 0) {
          promptBuilder = new StringBuilder(lines);
        }
        // odd lines are storing the choices
        else {
          choiceBuilder = new StringBuilder(lines);
          this.speech.setDisplay(promptBuilder.toString(), choiceBuilder.toString());
        }
        lineNum = lineNum + 1;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /* Switch to Next Game View**/
  public void openStampGame() {
    Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);
    startActivity(switchStampIntent);
  }

  /**
   * This method is called when user click the compare button Compare the user input with the
   * correct answer If the user input matches the answer, add points and go to successful result
   * page If the user input does not match the answer,keep the point and go to fail result page
   */
  public void compare(View view) {
    EditText editText = (EditText) findViewById(R.id.answer);
    String userInput = editText.getText().toString();
    boolean matches = userInput.toLowerCase().equals(this.correct.toLowerCase());
    if (matches) {
      Intent successfulIntent = new Intent(this, SuccessSpeechResult.class);
      // TODO:Add points for the user
      successfulIntent.putExtra(INPUT_MESSAGE, userInput);
      startActivity(successfulIntent);
    } else {
      Intent failIntent = new Intent(this, FailureSpeechResult.class);
      // TODO: Keep point for the user
      failIntent.putExtra(INPUT_MESSAGE, userInput);
      startActivity(failIntent);
    }
  }


  public void openPauseMenu(){
    Intent pauseMenuIntent = new Intent(this, PauseActivity.class);
    startActivityForResult(pauseMenuIntent, 1);
  }

  public void openMainMenu(){
    Intent mainMenuIntent = new Intent(this, MainActivity.class);
    startActivity(mainMenuIntent);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data){
    super.onActivityResult(requestCode, resultCode, data);

    //requestCode refers to the request code parameter of openPauseMenu's startActivityForResult call
    if (requestCode == 1) {
      if (resultCode == RESULT_OK){
        int userInput = data.getIntExtra("result", 0);

        if (userInput == 1){
          Log.i("Pause Result", "User has decided to resume play");
        }

        else if (userInput == 2){
          Log.i("Pause Result", "User has decided to quit the game");
          openMainMenu();
        }
      }

      else {
        Log.i("Result Code", "Result code is " + resultCode);
      }
    }
  }
}
