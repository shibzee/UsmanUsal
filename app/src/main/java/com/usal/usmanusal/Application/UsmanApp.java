package com.usal.usmanusal.Application;


import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sheriffdeen on 22/07/2017.
 */

public class UsmanApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

}
