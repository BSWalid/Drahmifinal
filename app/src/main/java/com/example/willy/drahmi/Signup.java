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

public class Signup extends AppCompatActivity {
    EditText user, pass,lastname,firstname;
    Button signup;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        db = new Database(this);
        db.open();
        lastname = (EditText) findViewById(R.id.name);
        firstname = (EditText) findViewById(R.id.prenom);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.signup);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String usertext = user.getText().toString();
                String passText = pass.getText().toString();
                String prenom =firstname.getText().toString();
                String name = lastname.getText().toString();

                Cursor c = db.getUserInfo(usertext, passText);
                if(c.getCount() != 0)
                {
                    Toast.makeText(getApplicationContext(), "You are already a member", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.insertUser(name,prenom, usertext, passText);
                    Toast.makeText(getApplicationContext(), "Thank you", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), TheLogin.class);
                    startActivity(i);
                }

            }
        });



    }

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
