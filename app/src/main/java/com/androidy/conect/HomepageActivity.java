package com.androidy.conect;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class HomepageActivity extends ListActivity {

    private List<ParseObject> mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        // Enable Local Datastore.
        // Parse.enableLocalDatastore(this);

        // Parse.initialize(this, "BHRhB0HdAtor61iF6e4D7MKmYRZsYZoNFXLtrWri", "ViTLvrDAPxi7TIIQFV2W6FT5ohhtHx7CfbjIK72s");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show the user the homepage
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("status");
            query.orderByDescending("createdAt");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> status, ParseException e) {

                    if (e == null) {

                        mStatus = status;

                        StatusAdapter adapter = new StatusAdapter(getListView().getContext() , mStatus);
                        setListAdapter(adapter);


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(HomepageActivity.this);
                        builder.setMessage(e.getMessage());
                        builder.setTitle("Sorry , There was an error");
                        builder.setPositiveButton("Okay" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.show();
                        dialog.show();

                    }

                }
            });

        } else {
            // show the signup or login screen
            Intent  intent = new Intent(HomepageActivity.this , LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.updateStatus:
                // take user to update status activity
                Intent intent = new Intent(HomepageActivity.this , UpdateStatusActivity.class);
                startActivity(intent);

                break;

            case R.id.logoutUser:
                // logout the user
                ParseUser.logOut();
                // take user back to log in screen
                Intent intent1 = new Intent(HomepageActivity.this , LoginActivity.class);
                startActivity(intent1);

                break;

        }

        return super.onOptionsItemSelected(item);
    }

}