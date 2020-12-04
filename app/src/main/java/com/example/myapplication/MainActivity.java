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
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Активность", "Метод onCreate вызван");
        setContentView(R.layout.activity_main);
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new UserListFragment(),"main_fragment").addToBackStack("main_fragment").commit();

    }
    @Override
    public void onStart(){
        super.onStart();
        Fragment fragment = new UserListFragment();
        // R.id.fragmentContainer - это FrameLayout из файла activity_main.xml
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment,"main_fragment").commit();
    }
    @Override
    public void onBackPressed() {
        Fragment currentFragment = fragmentManager.findFragmentByTag("main_fragment");
        if (currentFragment != null && currentFragment.isVisible()){
            super.onBackPressed();
        }else {
            Fragment fragment = new UserListFragment();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment").commit();
        }
    }
    public static void changeFragment(View view,User user){
        // Получаем хостинговую активность (в нашем случае MainActivity)
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Создаём менеджер фрагментов
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        // создаём фрагмент
        Fragment fragment = new UserFragment();
        // Создаём bundle (это как коллекция)
        Bundle bundle = new Bundle();
        // Записываем user в bundle для передачи в фрагмент
        bundle.putSerializable("user", user);
        // Кладём Bundle в фрагмент
        fragment.setArguments(bundle);
        //Заменяем фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

}