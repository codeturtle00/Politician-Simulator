package com.example.politicgame.Common;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/** This is an API for file reading and writing
 * All file saving and reading should be done by this API
 * You can view it as a thin layer to get access to database(json,txt)*/
public class FileSavingService {
  private Context context;
  public FileSavingService(Context context) {
    this.context = context;
  }
/** This reads a txt file given the fileName, returning the string read in the file*/
  public String readStringFile(String fileName) {
    StringBuilder textBuilder = new StringBuilder();
    try {
      FileInputStream fileInputStream = this.context.openFileInput(fileName);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String lines;
      int lineNum = 0;
      while ((lines = bufferedReader.readLine()) != null) {
        textBuilder.append(lines);
        lineNum = lineNum + 1;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return textBuilder.toString();
  }
/** This is writing a txt File given the fileName and the string to be saved into the txt*/
  public void writeStringFile(String textToSave, String fileName) {
    FileOutputStream outputStream;
    File rootDir = this.context.getFilesDir();
    try {
      outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
      outputStream.write(textToSave.getBytes());
      outputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** This is appending a txt File given the fileName and the string to be saved into the txt*/
  public void appendStringFile(String textToSave, String fileName) {
    FileOutputStream outputStream;
    File rootDir = this.context.getFilesDir();
    String oldString = this.readStringFile(fileName);
    StringBuilder savedText = new StringBuilder(oldString);
    savedText.append(textToSave);
    this.writeStringFile(savedText.toString(),fileName);
  }

  /** This is reading a json file given the fileName,returning a json array*/
  public JSONArray readJsonFile(String fileName) {
    String jsonString;
    JSONArray jsonArray = new JSONArray();
    try {
      FileInputStream fileInputStream = this.context.openFileInput(fileName);
      int size = fileInputStream.available();
      byte[] buffer = new byte[size];
      fileInputStream.read(buffer);
      fileInputStream.close();
      jsonString = new String(buffer, "UTF-8");
      jsonArray = new JSONArray(jsonString);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return jsonArray;
  }
  /** This is appending a json array given the fileName*/
  public void appendJsonArray(JSONArray jsonArray, String fileName) {
    JSONArray oldArray = this.readJsonFile(fileName);
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        oldArray.put(jsonArray.getJSONObject(i));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    writeJson(fileName, oldArray);
  }

  /** This is appending a json object given the fileName*/
  public void appendJsonObject(JSONObject jsonObject, String fileName) {
    JSONArray oldArray = this.readJsonFile(fileName);
    oldArray.put(jsonObject);
    writeJson(fileName, oldArray);
  }

  /** This is replacing a json object given the fileName, if the json object is not
   * found in the json file , then append the json object into the json file*/
  public void replaceJsonObject(JSONObject jsonObject, String fileName) {
    File rootDir = this.context.getFilesDir();
    JSONArray jsonArray = this.readJsonFile(fileName);
    String key = jsonObject.keys().next();
    boolean found = false;
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        if(jsonArray.getJSONObject(i).keys().next().equals(key)){
          jsonArray.getJSONObject(i).put(key, jsonObject.get(key));
          found = true;
        }
      }
      if (!found){
        this.appendJsonObject(jsonObject,fileName);
      }
      else{
      this.writeJson(fileName,jsonArray);}
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }

  /** This is writing a json file given a fileName and jsonArray*/
  public void writeJson(String fileName, JSONArray jsonArray) {
    File rootDir = this.context.getFilesDir();
    FileOutputStream outputStream;
    try {
      outputStream = this.context.openFileOutput(fileName, Context.MODE_PRIVATE);
      outputStream.write(jsonArray.toString().getBytes());
      outputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
