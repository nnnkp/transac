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

public class ClientDataSource {
    // Database fields
    private SQLiteDatabase database;
    private ClientSQLiteHelper dbHelper;
    private String[] allColumns = { ClientSQLiteHelper.COLUMN_ID,
            ClientSQLiteHelper.COLUMN_CLIENTNAME,ClientSQLiteHelper.COLUMN_EMAIL,
            ClientSQLiteHelper.COLUMN_MOBILE,ClientSQLiteHelper.COLUMN_NETAMOUNT,ClientSQLiteHelper.COLUMN_NETTYPE };

    public ClientDataSource(Context context) {
        dbHelper = new ClientSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Client createClient(String clientName, String email, String mobile, int netAmount, String netType) {
        ContentValues values = new ContentValues();
        values.put(ClientSQLiteHelper.COLUMN_CLIENTNAME, clientName);
        values.put(ClientSQLiteHelper.COLUMN_EMAIL, email);
        values.put(ClientSQLiteHelper.COLUMN_MOBILE, mobile);
        values.put(ClientSQLiteHelper.COLUMN_NETAMOUNT, netAmount);
        values.put(ClientSQLiteHelper.COLUMN_NETTYPE, netType);

        long insertId = database.insert(ClientSQLiteHelper.TABLE_CLIENT, null,
                values);
        Cursor cursor = database.query(ClientSQLiteHelper.TABLE_CLIENT,
                allColumns, ClientSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Client newClient = cursorToClient(cursor);
        cursor.close();
        return newClient;
    }

    public void deleteClient(Client client) {
        long id = client.getId();
        System.out.println("Client deleted with id: " + id);
        database.delete(ClientSQLiteHelper.TABLE_CLIENT, ClientSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<Client>();

        Cursor cursor = database.query(ClientSQLiteHelper.TABLE_CLIENT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Client Client = cursorToClient(cursor);
            clients.add(Client);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return clients;
    }



    private Client cursorToClient(Cursor cursor) {
       // Boolean isActive = (cursor.getInt(cursor.getColumnIndex("active")) == 1);
        Client client = new Client();
        client.setId(cursor.getLong(0));
        client.setClientName(cursor.getString(1));
        client.setEmail(cursor.getString(2));
        client.setMobile(cursor.getString(3));
        client.setNetAmount(cursor.getInt(4));
        client.setNetType(cursor.getString(5));
        return client;
    }
}

