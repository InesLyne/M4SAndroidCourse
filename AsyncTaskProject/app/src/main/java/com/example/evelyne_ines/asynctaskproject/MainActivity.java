package com.example.evelyne_ines.asynctaskproject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        image = (ImageView)findViewById(R.id.imageView);
        if (isOnline() == true)
        {
            DownloadImage download = new DownloadImage();
            download.execute();
        }
        else
        {
            Toast t = Toast.makeText(this, "Connect to the Internet first", Toast.LENGTH_SHORT );
            t.show();

        }

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

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    private class DownloadImage extends AsyncTask<URL, Integer, Bitmap>{
        @Override
        protected void onPreExecute(){
            Log.i("Methode", "onPreExecute Called");
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", "Downloading Image");
        }

        @Override
        protected Bitmap doInBackground(URL... urls) {
            Log.i("Methode", "doInBackground called");
            publishProgress(1);
            try {
                URL url = new URL("https://github.com/InesLyne/M4SAndroidCourse/raw/master/Yaounde.png");
                HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
                if (connexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new Exception("Failed to connect, verify your internet connexion");
                }
                InputStream inputStream = connexion.getInputStream();
                publishProgress(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;
            } catch (Exception e) {
                Log.e("Image", "Failed to load image", e);
                Log.e("error", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap img) {
            Log.i("Methode", "started");

            if (image != null && img != null) {
                Log.i("Retrieve", "Image");
                image.setImageBitmap(img);
                progressDialog.dismiss();
            }
        }


    }

}
