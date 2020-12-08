package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddUserFragment extends Fragment {
    private Button addUserBtn1;                  // кнопка Добвить пользователя (в базу)
    private EditText nameEditText1;              // поле Имя
    private EditText lastnameEditText1;          // поле Фамилия

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // запускаем родительский класс onCreate
    }
    @Override           // переопределяем View самого фрагмента (ел-т для отображения Активностью)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // onCreateView с аргументами Раздувателя, видимого контейнера и свитка сохраненого состояния экземпляра
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);     // создаём view, который раздувает fragment_add_user при помощи inflater метода inflate
        nameEditText1 = view.findViewById(R.id.nameEditText1);                     // связываем поле nameEditText c view'шкой по id
        lastnameEditText1 = view.findViewById(R.id.lastnameEditText1);             // связываем поле lastnameEditText c view'шкой по id
        addUserBtn1 = view.findViewById(R.id.addUserBtn1);                         // связываем кнопку addUserBtn c view'шкой по id
        addUserBtn1.setOnClickListener(new View.OnClickListener(){                  // обработка кнопки addUserBtn1
            @Override
            public void onClick(View view) {                                // добавляем метод обработки кнопки
                String userName = nameEditText1.getText().toString();       // записываем в пер. userName текст через метод nameEditText
                String userLastname = lastnameEditText1.getText().toString();// тем же способом получаем фамилию
                User user = new User();                                     // создаём пользователя
                user.setUserName(userName);                                 // передаем ему имя
                user.setUserLastName(userLastname);                         // передаем ему фамилию
                UserList userList = UserList.get(AddUserFragment.this);     // вызываем метод get у UserList и сохранем полученное в пер.userList
                userList.addUser(user);                                     // добавляем этого user в список userList
            }
        });
        return view;        // возвращаем return view;
    }

}

