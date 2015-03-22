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


import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

    private EditText mUsername;
    private EditText mPassword;
    private Button  mLoginButton;
    private Button mCreateAcountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Parse.enableLocalDatastore(this);

        //  Parse.initialize(this, "BHRhB0HdAtor61iF6e4D7MKmYRZsYZoNFXLtrWri", "ViTLvrDAPxi7TIIQFV2W6FT5ohhtHx7CfbjIK72s");

        mUsername = (EditText) findViewById(R.id.usernameLoginTextBox);

        mPassword = (EditText) findViewById(R.id.passwordLoginTextBox);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mCreateAcountButton = (Button) findViewById(R.id.createAccountLoginButton);

        mCreateAcountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });

        // listen for login button, then log in user using parse sdk

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        if (e == null) {
                            Toast.makeText(LoginActivity.this , "Welcome Back" , Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this , HomepageActivity.class);
                            startActivity(intent);

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

   /*
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
