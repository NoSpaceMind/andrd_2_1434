package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.myapplication.database.UserBaseHelper;
import com.example.myapplication.database.UserDbSchema;

import java.util.ArrayList;
import java.util.List;

// Синглетный класс (может быть создан только один объект)
public class UserList {
    private static UserList userList;
    private Context context;            // контекст context
    private SQLiteDatabase database;    // БД SQLite
    private List users;                 // список users

    public static UserList get(Context context){
        if(userList == null){                   // проверяем существует ли userList
            userList = new UserList(context);   // Если нет, то создаём
        }
        return userList;                        // либо возвращаем тот, что есть
    }
    private UserList(Context context){                  // наполение списка пользователями
        this.context = context.getApplicationContext();
        database = new UserBaseHelper(context).getWritableDatabase();
    }
    public List getUsers(){                     // курсор ячеек пользователей
        users = new ArrayList();
        UserCursorWrapper cursor = queryUsers(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return users;
    }
    public void addUser(User user){                     // метод добавленя пользователя в коллекцию(передаем сюда user'a)
        ContentValues values = getContentValues(user);  // берем  пользователя и через метод ContentValues добавляем
        database.insert(UserDbSchema.UserTable.NAME, null, values); //Через insert добавляем в базу UserDbSchema.UserTable.NAME
    }

    private static ContentValues getContentValues(User user){   //  запись и обновление БД
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.UUID, user.getUuid().toString());    // название столбца/значение
        values.put(UserDbSchema.UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.USERLASTNAME, user.getUserLastName());
        return values;
    }

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs){   // метод чтения БД
        Cursor cursor = database.query(                                             // query - выборка из БД
                UserDbSchema.UserTable.NAME,                                        // название таблицы БД
                null,                                                       // далее идут столбцы
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new UserCursorWrapper(cursor);
    }

    public void updateUser(User user){                  // редактируем пользователя
        String uuidString = user.getUuid().toString();
        ContentValues values = getContentValues(user);

        database.update(UserDbSchema.UserTable.NAME,values,
                UserDbSchema.UserTable.Cols.UUID+"=?",
                new String[]{uuidString});
    }

    public void deleteUser(User user){                  // удаляем пользователя
        String uuidString = user.getUuid().toString();
        int delCount = database.delete(UserDbSchema.UserTable.NAME, UserDbSchema.UserTable.Cols.UUID+"=?",
                new String[]{uuidString});
    }
}