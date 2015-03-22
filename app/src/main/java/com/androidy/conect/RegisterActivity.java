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

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {

    protected EditText mUserName;
    protected EditText mUserEmail;
    protected EditText mUserPassword;

    protected Button mRegisterButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // initailaze edit text



       mUserName = (EditText) findViewById(R.id.usernameRegisterEditText);
        mUserEmail = (EditText) findViewById(R.id.emailRegisterEditText);
        mUserPassword = (EditText) findViewById(R.id.passwordRegisterEditText);

        mRegisterButton = (Button) findViewById(R.id.signUpButton);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  name =  mUserName.getText().toString().trim();
                String email = mUserEmail.getText().toString().trim();
                String password = mUserPassword.getText().toString().trim();

                if (name != "") {
                    if (email != "") {
                        if (password.length() > 4) {

                            ParseUser user = new ParseUser();
                            user.setUsername(name);
                            user.setEmail(email);
                            user.setPassword(password);

                            user.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(com.parse.ParseException e) {

                                    if (e == null) {
                                        // user signed up successfully
                                        Toast.makeText(RegisterActivity.this , "Welcome!" , Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this , HomepageActivity.class);
                                        startActivity(intent);

                                    } else {
                                        // handle error while signing up user advise user
                                    }

                                }
                            });



                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Sorry, please create a longer password for security purposes!");
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
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Please fill in your email for security purposes!");
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
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Please create a username before signing up!");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
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