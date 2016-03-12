package com.github.daquarischadwick.scoutingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] values;
    private ArrayList<String[]> List;
    /*
    * Create a list,
    * When user presses the submit button
    *   save info to "values" then add the string array to the list
    *   the, upload the file to server
    * */

    private Button submit;
    private EditText teamNumText;
    private EditText pointNumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List = new ArrayList<>();
        values = new String[3];

        submit = (Button)findViewById(R.id.submitButton);
        teamNumText = (EditText)findViewById(R.id.teamNumText);
        pointNumText = (EditText)findViewById(R.id.pointNumText);

        submit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        values[0] = teamNumText.getText().toString();
                        values[1] = pointNumText.getText().toString();
                        List.add(values);
                    }
                }
        );
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
