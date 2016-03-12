package com.github.daquarischadwick.scoutingapp;

/*
    * Create a list,
    * When user presses the submit button
    *   save info to "values" then add the string array to the list
    *   the, upload the file to server
    *   maybe add an optional picture option to take a picture of the robot
    * */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String DATAFILE = "datafile.txt";
    public final int VALNUMBER = 3;     //number of values to be taken
    public static String EXTRA_DATA = "com.github.daquarischadwick.scoutingapp.DATA";

    private String[] values;
    private ArrayList<String[]> List;

    private Button submit;
    private Button viewData;
    private EditText teamNumText;
    private EditText pointNumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List = new ArrayList<>();   //To hold different sets of values
        values = new String[3]; //Different values to store. Respective to Layout

        submit = (Button)findViewById(R.id.submitButton);   //Saves current sheet
        viewData = (Button)findViewById(R.id.viewData);     //View all data collected
        teamNumText = (EditText)findViewById(R.id.teamNumText);
        //teamNumText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        pointNumText = (EditText)findViewById(R.id.pointNumText);

        /*Save data to file and reset values*/
        submit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        values[0] = "Team " + teamNumText.getText().toString();
                        values[1] = "Points: " + pointNumText.getText().toString();
                        values[2] = "---------------";
                        List.add(values);

                        /*Reset text on screen to make re-entering information a lot easier*/
                        teamNumText.setText("");
                        pointNumText.setText("");

                        saveData();
                    }
                }
        );

        /*Long click to reset file for new data*/
        submit.setOnLongClickListener(
                new View.OnLongClickListener(){
                    public boolean onLongClick(View v){
                        refreshData();
                        return true;
                    }
                }
        );

        /*View current data*/
        viewData.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        toDebugActivity();
                    }
                }
        );
    }

    /*Refreshes file*/
    public void refreshData(){//Writing NEW allows for data refreshing

        try{
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(DATAFILE, MODE_PRIVATE));
            out.write("");
            out.close();
            Toast.makeText(this, "New File Created", Toast.LENGTH_LONG).show();
        }catch (Throwable t){

            Toast.makeText(this, "ERROR: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /*Save data to a file
    *
    * This file should later be sent to server
    * */
    public void saveData(){

        //To-Do: Check if either is text field is empty, give error if so and do not save

        try {
            StringBuilder toFile = new StringBuilder(); //Builds a string to be given to file
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(DATAFILE, MODE_APPEND)); //Opens the file
            for (String s : values)
                toFile.append(s + "\n");
            out.write(toFile.toString());
            out.close();

            Toast.makeText(this, "Data Saved to: " + this.getFilesDir().getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (Throwable t) {

            Toast.makeText(this, "ERROR: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /*Go to the debug activity to view Data collected*/
    private void toDebugActivity(){

        Intent debugAct = new Intent(this, DebugActivity.class);
        debugAct.putExtra(EXTRA_DATA, DATAFILE);
        startActivity(debugAct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
