package com.example.evelyne_ines.firebasestudent;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by evelyne-ines on 29/07/16.
 */
public class Config extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
