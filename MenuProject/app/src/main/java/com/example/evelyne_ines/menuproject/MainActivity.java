package com.example.evelyne_ines.menuproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnsms;
    private Button btnphone;
    private Button btnweb;
    private Button btnmap;
    private Button btnshare;
    private Button btnactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnsms = (Button)findViewById(R.id.btnsms);
        btnsms.setOnClickListener(this);
        btnphone = (Button)findViewById(R.id.btnphone);
        btnphone.setOnClickListener(this);
        btnweb = (Button)findViewById(R.id.btnweb);
        btnweb.setOnClickListener(this);
        btnmap = (Button)findViewById(R.id.btnmap);
        btnmap.setOnClickListener(this);
        btnshare = (Button)findViewById(R.id.btnshare);
        btnshare.setOnClickListener(this);
        btnactivity = (Button)findViewById(R.id.btnactivity);
        btnactivity.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
            Toast t = Toast.makeText(this, "Settings", Toast.LENGTH_SHORT );
            t.show();
            return true;
        }else if (id == R.id.action_help){
            final Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick (View v){
        switch (v.getId()){
            case R.id.btnsms: {
                final Intent intentsms = new Intent(Intent.ACTION_SENDTO);
                intentsms.setData(Uri.parse("smsto:" + Uri.encode("00221781888329")));
                intentsms.putExtra("sms_body", "NTONGA Evelyne Inès");
                startActivity(intentsms);
            }break;

            case R.id.btnphone: {
                final Intent intentphone = new Intent(Intent.ACTION_DIAL);
                intentphone.setData(Uri.parse("tel: 00221781888329"));
                startActivity(intentphone);
            }break;

            case R.id.btnweb: {
                final Intent intentweb = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.sn"));
                startActivity(intentweb);
            }break;

            case R.id.btnmap: {
                String geoUri = String.format("geo:3.872850, 11.532919");
                Uri geo = Uri.parse(geoUri);
                final Intent intentmap = new Intent(Intent.ACTION_VIEW, geo);
                startActivity(intentmap);
            }break;

            case R.id.btnshare: {
                final Intent intentshare = new Intent(Intent.ACTION_SEND);
                intentshare.setType("Text/plain");
                intentshare.putExtra(Intent.EXTRA_SUBJECT, "M4SAndroidCourse");
                intentshare.putExtra(Intent.EXTRA_TEXT, "Join the Course");
                startActivity(Intent.createChooser(intentshare, "Share The Love"));
            }break;

            case R.id.btnactivity: {
                final Intent intent = new Intent(this, NewActivity.class);
                startActivity(intent);
            }break;

        }

    }
}
