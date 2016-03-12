package com.github.daquarischadwick.scoutingapp;

/*
    * Create a list,
    * When user presses the submit button
    *   save info to "values" then add the string array to the list
    *   the, upload the file to server
    * */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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
    public static String EXTRA_DATA = "com.github.daquarischadwick.scoutingapp.DATA";

    private String[] values;
    private ArrayList<String[]> List;

    private Button submit;
    private EditText teamNumText;
    private EditText pointNumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List = new ArrayList<>();   //To hold different sets of values
        values = new String[3]; //different values to store. Respective to Layout

        submit = (Button)findViewById(R.id.submitButton);
        teamNumText = (EditText)findViewById(R.id.teamNumText);
        pointNumText = (EditText)findViewById(R.id.pointNumText);

        submit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        values[0] = teamNumText.getText().toString();
                        values[1] = pointNumText.getText().toString();
                        List.add(values);
                        saveData();
                        toDebugActivity();
                    }
                }
        );
    }

    public void saveData(){

        try{
            StringBuilder toFile = new StringBuilder();
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(DATAFILE, 0));
            for(String s : values)
                toFile.append(s + "\n");
            out.write(toFile.toString());
            out.close();

            Toast.makeText(this, "Data Saved to: " + this.getFilesDir().getAbsolutePath(), Toast.LENGTH_LONG).show();
        }catch(Throwable t){

            Toast.makeText(this, "ERROR: "+t.toString(), Toast.LENGTH_LONG).show();
        }
    }

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
