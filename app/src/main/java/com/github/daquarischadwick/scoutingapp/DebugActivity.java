package com.github.daquarischadwick.scoutingapp;

/**See what data you have entered into the application
 *
 *  To-Do: Make into list view and add items based on team number
 *  check team number before adding,
 *      if similar to team number that has already been taken, overwrite previous values
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DebugActivity extends AppCompatActivity {

    private Intent intt;

    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        intt = getIntent();
        text = new TextView(this);
        text.setTextSize(18f);
        RelativeLayout rel = (RelativeLayout)findViewById(R.id.content);
        showNewFile();
        rel.addView(text);
        //String message = intt.getStringExtra(MainActivity.EXTRA_DATA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_debug, menu);
        return true;
    }

    private void showNewFile(){

        try{

            InputStream in = openFileInput(intt.getStringExtra(MainActivity.EXTRA_DATA));
            if(in != null){

                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(tmp);
                String str;
                StringBuilder bul = new StringBuilder();
                while((str = buf.readLine()) != null){

                    bul.append(str + '\n');
                }
                in.close();

                text.setText(bul.toString());
            }
        }catch(java.io.FileNotFoundException e){

            /*problem*/
            Toast.makeText(this, "ERROR: NO SUCH FILE", Toast.LENGTH_LONG).show();
        }catch (Throwable t){

            Toast.makeText(this, "ERROR: " + t.toString(), Toast.LENGTH_LONG).show();
        }
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
