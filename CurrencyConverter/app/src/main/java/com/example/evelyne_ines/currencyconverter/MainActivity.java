package com.example.evelyne_ines.currencyconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText input;
    private TextView output;
    private Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnsubmit = (Button)findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
        input = (EditText)findViewById(R.id.etinput);
        output = (TextView)findViewById(R.id.tvoutput);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "evelyneines16@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnsubmit){
            Spinner spinner = (Spinner)findViewById(R.id.spinner);
            String choice = spinner.getSelectedItem().toString();
            Log.e("valeur", choice);
            if(input.getText().toString().trim().equals("")){
                input.setError("This field cannot be empty!");
            }else {
                Log.e("info", "Empty");
                conversion(choice);
            }
        }
    }
    public void conversion (String choice){
        Float f = Float.parseFloat(input.getText().toString());

        if(choice.equals("Dollars")){
            Float results = new Float(f * 586.84);
            output.setText(results.toString()+"\n Francs CFA");
        }else{
            Float results = new Float(f / 586.84);
            output.setText(results.toString() + "\n Dollars");
        }
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
