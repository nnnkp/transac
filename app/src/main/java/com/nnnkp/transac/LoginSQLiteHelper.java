package com.nnnkp.transac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class LoginSQLiteHelper extends SQLiteOpenHelper {
    public LoginSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_ACTIVE = "active";


    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * To add boolean in SQLite
     * flag INTEGER DEFAULT 0
     *
     * To retrieve Boolean
     * Boolean flag = (cursor.getInt(cursor.getColumnIndex("flag")) == 1);
     */
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USER + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_USERNAME + " text not null, "
            + COLUMN_EMAIL + " text not null, "+ COLUMN_PASSWORD + " text not null, "+ COLUMN_MOBILE
            + " text not null, "+COLUMN_ACTIVE+" integer default 0);";

    public LoginSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LoginSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
  
}
