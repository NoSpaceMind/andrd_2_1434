package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class AddUser extends AppCompatActivity{
    private Button addUserBtn;                  // кнопка Добвить пользователя (в базу)
    private EditText nameEditText;              // поле Имя
    private EditText lastnameEditText;          // поле Фамилия
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // запускаем родительский класс onCreate
        setContentView(R.layout.activity_add_user);                         // размещаем activity_add_user на эране
        nameEditText = findViewById(R.id.nameEditText);                     // связываем поле nameEditText по id
        lastnameEditText = findViewById(R.id.lastnameEditText);             // связываем поле lastnameEditText по id
        addUserBtn = findViewById(R.id.addUserBtn);                         // связываем кнопку addUserBtn по id
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                            // добавляем метод обработки кнопки
                String userName = nameEditText.getText().toString();        // записываем в пер. userName текст через метод nameEditText
                String userLastname = lastnameEditText.getText().toString();// тем же способом получаем фамилию
                User user = new User();                                     // создаём пользователя
                user.setUserName(userName);                                 // передаем ему имя
                user.setUserLastName(userLastname);                         // передаем ему фамилию
                UserList userList = UserList.get(AddUser.this);             // вызываем метод get у UserList
                userList.addUser(user);                                     // добавляем этого user в список userList
                onBackPressed();                                            // обработка нажатия кнопки "назад"
            }
        });
    }
}