package com.example.module3assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module3assignment.myfiles.User;
import com.example.module3assignment.myfiles.UserDatabase;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Reference to all the buttons and other controls on the layout
    // Reminder: all referenced variables from layout must be outside
    // the create method

    private UserDatabase userDatabase;

    Button login_btn, newUser_btn, clickMe;
    EditText username_et, password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning the layout stuff to variables
        login_btn = findViewById(R.id.login_btn);
        newUser_btn = findViewById(R.id.newUser_btn);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);

        UserDatabase userDatabase = new UserDatabase(MainActivity.this);

        // Handles logging in
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Logging in in User", Toast.LENGTH_SHORT).show();
            }
        });

        // Handles New Users
        newUser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                // Creates a new userDatabase
                //UserDatabase userDatabase = new UserDatabase(MainActivity.this);

                // If either fields are blank... inform user
                if(TextUtils.isEmpty(username_et.getText().toString()) ||
                TextUtils.isEmpty(password_et.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter a username and password", Toast.LENGTH_SHORT).show();
                }
                // If username is already taken... inform user
                else {
                    List<User> userList = userDatabase.getAllUsers(); // gets the current list of users
                    // Checking all usernames
                    for (int i = 0; i < userList.size(); ++i) {
                        if (userList.get(i).getUserName().equals(username_et.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Username " + username_et.getText().toString() +
                                    " is already taken...", Toast.LENGTH_SHORT).show();
                            return; // exits the method if username is found
                        }
                        else {
                            Toast.makeText(MainActivity.this, userList.get(i).getUserName(),Toast.LENGTH_SHORT).show();
                            // continue...
                        }
                    }
                    // if username isn't found...create new user
                    user = new User(-1, username_et.getText().toString(), password_et.getText().toString());
                    Toast.makeText(MainActivity.this, user.toString() , Toast.LENGTH_SHORT).show();
                    boolean success = userDatabase.addOne(user);
                    Toast.makeText(MainActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

//// NOTES ////
// This is how you would transition to the next screen...
// need a way to transfer the data once obtained to the next screen.
// Then you need to populate the second screen with that data if it isn't
// empty.

// Intent intent = new Intent(MainActivity.this, ItemLogScreen.class);
// startActivity(intent);
