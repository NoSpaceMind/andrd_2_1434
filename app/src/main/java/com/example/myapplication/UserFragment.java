package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserFragment extends Fragment {                // наследуемся от Фрагмента (ф-та)
    private User user;
    private TextView userName_userLastname_View;
    private EditText editName;
    private EditText editLastname;
    private Button updateBtn;
    private UserList userList;
    private Button delBtn;


    @Override           // переопределяем метод onCreate
    public void onCreate(Bundle savedInstanceState){        // действия, когда создаём ф-т
        super.onCreate(savedInstanceState);                 // запускаем родительский класс onCreate
        Bundle bundle = getArguments();                     // Записываем в свиток Аргументы
        user = (User) bundle.getSerializable("user");   // Принимаем объект user
    }
    @Override           // переопределяем View самого фрагмента (ел-т для отображения Активностью)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){ // onCreateView с аргументами Раздувателя, видимого контейнера и свитка сохраненого состояния экземпляра
        View view = inflater.inflate(R.layout.fragment_user,container,false);  // создаём view, который раздувает fragment_user при помощи inflater метода inflate
        userList = UserList.get(getActivity());
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);    // связываем поле userName_userLastname_View c view'шкой по id
        editName = view.findViewById(R.id.editName);                                        // связываем поле editName c view'шкой по id
        editLastname = view.findViewById(R.id.editLastname);                                // связываем поле editLastname c view'шкой по id
        updateBtn = view.findViewById(R.id.updateBtn);                                      // связываем кнопку updateBtn c view'шкой по id
        delBtn = view.findViewById(R.id.delBtn);                                            // связываем кнопку delBtn c view'шкой по id
        final String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        userName_userLastname_View.setText(userName);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                            // кнопка внести изменения
                String newUserName = editName.getText().toString();
                String newUserLastname = editLastname.getText().toString();
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastname);
                userList.updateUser(user);
                Toast.makeText(getActivity(),"Данные изменены",Toast.LENGTH_SHORT).show();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                   // кнопка удалить пользователя
                userList.deleteUser(user);
                Toast.makeText(getActivity(),"Пользователь удален",Toast.LENGTH_SHORT).show();
            }
        });
        return view;        // возвращаем return view;
    }
}
