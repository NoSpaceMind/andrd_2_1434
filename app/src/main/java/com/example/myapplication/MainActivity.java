package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();  //  ФрагментМенеджер для управления фрагментами (перехода по ним)

    @Override
    protected void onCreate(Bundle savedInstanceState) {        // создание Активности
        super.onCreate(savedInstanceState);
        Log.d("Активность", "Метод onCreate вызван");
        setContentView(R.layout.activity_main);                 // определяем View для activity_main
        // beginTransaction - метод, который помещает эл-т на экран
        // add имеет 2 аргумента: (1)id того, что будет отображаться (2)сам фрагмент, что планируем размещать
        // commit() - размещаем на экране
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new UserListFragment(),"main_fragment").addToBackStack("main_fragment").commit();

    }
    @Override
    public void onStart(){                                      // запуск Активности
        super.onStart();                                        // запускаем родительский класс onStart
        Fragment fragment = new UserListFragment();             // создаём Фрагмент из UserListFragment
        // R.id.fragmentContainer - это FrameLayout из файла activity_main.xml
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment,"main_fragment").commit();

    }
    @Override
    public void onBackPressed() {                               // переопределение кнопки "назад"
        Fragment currentFragment = fragmentManager.findFragmentByTag("main_fragment");
        if (currentFragment != null && currentFragment.isVisible()){
            super.onBackPressed();
        }else {
            Fragment fragment = new UserListFragment();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment").commit();
        }
    }

    public static void changeFragment(View view,User user){                     // метод смены фрагмента

        FragmentActivity activity = (FragmentActivity) view.getContext();       // Получаем хостинговую активность (в нашем случае MainActivity)
        FragmentManager fragmentManager = activity.getSupportFragmentManager(); // Создаём менеджер фрагментов
        Fragment fragment = new UserFragment();                                 // создаём фрагмент
        Bundle bundle = new Bundle();                                           // Создаём bundle (это как коллекция)
        bundle.putSerializable("user", user);                                   // Записываем user в bundle для передачи в фрагмент
        fragment.setArguments(bundle);                                          // Кладём Bundle в фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();//Заменяем фрагмент
    }

}