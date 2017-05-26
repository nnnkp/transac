package com.nnnkp.transac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class UserDataSource {
    // Database fields
    private SQLiteDatabase database;
    private LoginSQLiteHelper dbHelper;
    private String[] allColumns = { LoginSQLiteHelper.COLUMN_ID,
            LoginSQLiteHelper.COLUMN_USERNAME,LoginSQLiteHelper.COLUMN_EMAIL,
            LoginSQLiteHelper.COLUMN_PASSWORD,LoginSQLiteHelper.COLUMN_MOBILE,LoginSQLiteHelper.COLUMN_ACTIVE };

    public UserDataSource(Context context) {
        dbHelper = new LoginSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(String username, String email,String password, String mobile, Boolean isActive) {
        ContentValues values = new ContentValues();
        values.put(LoginSQLiteHelper.COLUMN_USERNAME, username);
        values.put(LoginSQLiteHelper.COLUMN_EMAIL, email);
        values.put(LoginSQLiteHelper.COLUMN_PASSWORD, password);
        values.put(LoginSQLiteHelper.COLUMN_MOBILE, mobile);
      //  values.put(LoginSQLiteHelper.COLUMN_ACTIVE, isActive);
        long insertId = database.insert(LoginSQLiteHelper.TABLE_USER, null,
                values);
        Cursor cursor = database.query(LoginSQLiteHelper.TABLE_USER,
                allColumns, LoginSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public void deleteUser(User user) {
        long id = user.getId();
        System.out.println("User deleted with id: " + id);
        database.delete(LoginSQLiteHelper.TABLE_USER, LoginSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(LoginSQLiteHelper.TABLE_USER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User User = cursorToUser(cursor);
            users.add(User);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        Boolean isActive = (cursor.getInt(cursor.getColumnIndex("active")) == 1);
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setUsername(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        user.setPassword(cursor.getString(3));
        user.setMobile(cursor.getString(4));
        user.setActive(isActive);
        return user;
    }
}

