package com.nnnkp.transac;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by npraj1 on 5/23/2017.
 */

public class ClientSQLiteHelper extends SQLiteOpenHelper {
    public ClientSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static final String TABLE_CLIENT = "client";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLIENTNAME = "clientName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_NETAMOUNT = "netAmount";
    public static final String COLUMN_NETTYPE = "netType";


    private static final String DATABASE_NAME = "client.db";
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
            + TABLE_CLIENT + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_CLIENTNAME + " text, "
            + COLUMN_EMAIL + " text, "+ COLUMN_MOBILE
            + " text, "+COLUMN_NETAMOUNT+" integer default 0, "+COLUMN_NETTYPE+" text);";

    public ClientSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ClientSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        onCreate(db);
    }

    //Android Database Manager

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
  
}