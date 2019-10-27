package com.example.politicgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private static final String EXAMPLE_FILE = "SpeechPrompt.txt";
    private final int POINTSGIVEN = 1;
    private static final String TAG = "Main Activity";
    private String correct;
    public static final String INPUT_MESSAGE = "politicgame.speech.input.MESSAGE";
    public static final String  CORRECTION_MESSAGE = "politicgame.speech.result.MESSAGE";

    private void setSpeech(SpeechGame speech){
        this.speech = speech;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.setSpeech(new SpeechGame());
        final Button button = findViewById(R.id.speechNext);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openStampGame();
                    }
                });
        writeFile();
        System.out.println("Hello");
        TextView prompt = findViewById(R.id.prompt);
        TextView choiceA = findViewById(R.id.ChoiceA);
        TextView choiceB = findViewById(R.id.ChoiceB);
        TextView choiceC = findViewById(R.id.ChoiceC);
        TextView choiceD = findViewById(R.id.ChoiceD);
        System.out.println("Prompt" + this.speech.getPrompt());
        Set<String> speechData = this.speech.getDisplay().keySet();
        Random r = new Random();
        int num = r.nextInt(speechData.size());
        String promptKey = (String) speechData.toArray()[num];
        prompt.setText(promptKey);
            String choices = this.speech.getDisplay().get(promptKey);
            String[] result = choices.split(",");
            TextView[] textViews = {choiceA, choiceB, choiceC, choiceD};
            for (int i =0; i < 4; i ++){
                if (!result[i].contains("*")){
                    this.correct = result[i].trim();
                }
                textViews[i].setText(result[i].trim());
            }
        }

    private void writeFile() {
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
        String filename = "SpeechPrompts.txt";
        FileOutputStream outputStream;
        File rootDir = getFilesDir();
        Log.i(TAG, "root directory is " + rootDir);
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(textToSave.getBytes());
            outputStream.close();
            readFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void readFile() {
        try{
            FileInputStream fileInputStream = openFileInput("SpeechPrompts.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lines;
            StringBuilder promptBuilder = new StringBuilder();
            StringBuilder choiceBuilder = new StringBuilder();
            int i = 0;
            while ((lines = bufferedReader.readLine()) != null){
                if (i % 2 == 0){
                    promptBuilder  = new StringBuilder(lines);}
                else{
                    choiceBuilder = new StringBuilder(lines);
                    this.speech.setDisplay(promptBuilder.toString(), choiceBuilder.toString());
                    System.out.println("Prompt" + promptBuilder.toString());
                    System.out.println("Choice" + choiceBuilder.toString());
            }
                i = i + 1;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
    }

    public void openStampGame() {
        Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);
        startActivity(switchStampIntent);
    }

    /**
     * Compare the user input with the answer
     * If the user input matches the answer, add points and show successful hints
     * If the user input does not match the answer, keep the point and show the right answer*/
    public void compare(View view) {
        Intent intent = new Intent(this, SpeechResult.class);
        EditText editText = (EditText) findViewById(R.id.answer);
        String userInput = editText.getText().toString();
        //boolean matches = userInput.equals(this.correct);
        intent.putExtra(INPUT_MESSAGE, userInput);
        startActivity(intent);

    }

}