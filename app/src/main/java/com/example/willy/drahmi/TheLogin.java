package com.example.willy.drahmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TheLogin extends AppCompatActivity {
    EditText user, pass;
    Button login, signup;
    TextView tv;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_login);



        db = new Database(this);
        db.open();

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usertext = user.getText().toString();
                String passText = pass.getText().toString();

                Cursor c = db.getUserInfo(usertext, passText);
                if(c.getCount() != 0)
                {


                    Intent i = new Intent(getApplicationContext(), Accounts.class);
                    startActivity(i);



                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  Intent i = new Intent(getApplicationContext(), Signup.class);
                  startActivity(i);



            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
    }

