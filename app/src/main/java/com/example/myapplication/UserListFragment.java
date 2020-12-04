package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserListFragment extends Fragment {
    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;
    private Button openAddUserActivity;
    @Override // Метод создаёт компонент View фрагмента из XML разментки
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_list,viewGroup,false);
        userRecyclerView = view.findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        openAddUserActivity = view.findViewById(R.id.openAddUserActivity);
        UserList userList = UserList.get(getActivity());
        List<User> users = userList.getUsers();
        userAdapter = new UserAdapter(users);
        userRecyclerView.setAdapter(userAdapter);
        openAddUserActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddUser.class));
            }
        });
        return view;
    }

    // Класс UserHolder формирует элементы списка
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView userItemTextView;
        private User itemUser;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item_user,viewGroup,false));
            // itemView - это элемент списка
            userItemTextView = itemView.findViewById(R.id.userItem);
            itemView.setOnClickListener(this);
        }
        public void bind(User user){
            itemUser = user;
            String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName()+"\n---------";
            userItemTextView.setText(userName); // Устанавливаем текст элемента списка
        }

        @Override
        public void onClick(View view) {
            MainActivity.changeFragment(view, itemUser);
        }
    }

    // Класс UserAdapter отдаёт элементы в RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<User> users;
        public UserAdapter(List<User> users){
            this.users = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            userHolder.bind(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

}
