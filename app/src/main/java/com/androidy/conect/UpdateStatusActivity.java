package com.androidy.conect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UpdateStatusActivity extends Activity {

    private Button mStatusUpdateButton;
    private EditText mStatusUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        // initialize


       mStatusUpdate = (EditText) findViewById(R.id.updateStatusTextBox);
        mStatusUpdateButton = (Button) findViewById(R.id.statusUpdateBtn);

        mStatusUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current user

                ParseUser currentUser = ParseUser.getCurrentUser();
                String currentUserUsername = currentUser.getUsername();


                String newStatus = mStatusUpdate.getText().toString();

                if (newStatus.isEmpty()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                    builder.setMessage("Sorry! Status Should Not Be Empty");
                    builder.setTitle("Ooops");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.show();
                    dialog.show();


                } else {

                    // save the status in parse backend cloud

                    ParseObject statusObject = new ParseObject("status");  // "status" is the class name
                    statusObject.put("newStatus", newStatus);
                    statusObject.put("user", currentUserUsername); // put user name of user who put this status
                    statusObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                //successfully saved the status update

                                Toast.makeText(UpdateStatusActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(UpdateStatusActivity.this, HomepageActivity.class);
                                startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                                builder.setMessage(e.getMessage());
                                builder.setTitle("Sorry , There was an error uploading your post!");
                                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
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

                }
            }
        });
    }
/*



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.update_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
     */
}