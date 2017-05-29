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

public class TransactionDataSource {
    // Database fields
    private SQLiteDatabase database;
    private TransactionSQLiteHelper dbHelper;
    private String[] allColumns = { TransactionSQLiteHelper.COLUMN_ID,
            TransactionSQLiteHelper.COLUMN_CLIENTNAME,TransactionSQLiteHelper.COLUMN_AMOUNT,
            TransactionSQLiteHelper.COLUMN_TYPE,TransactionSQLiteHelper.COLUMN_REMARKS,TransactionSQLiteHelper.COLUMN_TRANSACTIONDATE};

    public TransactionDataSource(Context context) {
        dbHelper = new TransactionSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Transaction createTransaction(String clientName, int amount, String type,String remarks, String date) {
        ContentValues values = new ContentValues();
        values.put(TransactionSQLiteHelper.COLUMN_CLIENTNAME, clientName);
        values.put(TransactionSQLiteHelper.COLUMN_AMOUNT, amount);
        values.put(TransactionSQLiteHelper.COLUMN_TYPE, type);
        values.put(TransactionSQLiteHelper.COLUMN_REMARKS, remarks);
        values.put(TransactionSQLiteHelper.COLUMN_TRANSACTIONDATE, date);
        
        long insertId = database.insert(TransactionSQLiteHelper.TABLE_TRANSACTION, null,
                values);
        Cursor cursor = database.query(TransactionSQLiteHelper.TABLE_TRANSACTION,
                allColumns, TransactionSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Transaction newTransaction = cursorToTransaction(cursor);
        cursor.close();
        return newTransaction;
    }

    public void deleteTransaction(Transaction transaction) {
        long id = transaction.getId();
        System.out.println("Transaction deleted with id: " + id);
        database.delete(TransactionSQLiteHelper.TABLE_TRANSACTION, TransactionSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();

        Cursor cursor = database.query(TransactionSQLiteHelper.TABLE_TRANSACTION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Transaction Transaction = cursorToTransaction(cursor);
            transactions.add(Transaction);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return transactions;
    }

    private Transaction cursorToTransaction(Cursor cursor) {
       // Boolean isActive = (cursor.getInt(cursor.getColumnIndex("active")) == 1);
        Transaction transaction = new Transaction();
        transaction.setId(cursor.getLong(0));
        transaction.setClientName(cursor.getString(1));
        transaction.setAmount(cursor.getInt(2));
        transaction.setType(cursor.getString(3));
        transaction.setRemarks(cursor.getString(4));
        transaction.setDate(cursor.getString(5));
        return transaction;
    }
}

