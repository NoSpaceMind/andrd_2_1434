package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddUser extends AppCompatActivity {
    private Button addUserBtn;
    private EditText nameEditText;
    private EditText lastnameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        nameEditText = findViewById(R.id.nameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = nameEditText.getText().toString();
                String userLastname = lastnameEditText.getText().toString();
                User user = new User();
                user.setUserName(userName);
                user.setUserLastName(userLastname);
                UserList userList = UserList.get(AddUser.this);
                userList.addUser(user);
                onBackPressed();
            }
        });
    }
}