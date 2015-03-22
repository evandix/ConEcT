package com.androidy.conect;

import com.parse.Parse;

/**
 * Created by christinajackey on 3/21/15.
 */
public class Application extends android.app.Application {

    public void onCreate() {
        // Enable Local Datastore.
     //   Parse.enableLocalDatastore(this);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "Vv4yUWTwIN7lPh8hzDkKBiruECt6r3RARpaHWWW0", "rRF9g5kUEunK0j8ZlIuR5nuGSCOZeIp5Y3PHOLdg");

    }

}