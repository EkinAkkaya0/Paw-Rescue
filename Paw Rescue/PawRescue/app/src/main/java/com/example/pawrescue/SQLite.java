package com.example.pawrescue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdetails.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_USER_ID + " TEXT PRIMARY KEY," +
            COLUMN_NAME + " TEXT," +
            COLUMN_SURNAME + " TEXT)";

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean saveUserData(String userID, String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userID);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result != -1;
    }

    public Cursor retrieveUserData(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_NAME, COLUMN_SURNAME};
        String selection = COLUMN_USER_ID + "=?";
        String[] selectionArgs = {userID};

        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.getColumnCount() > 0) {
            if (cursor.moveToFirst()) {
                return cursor;
            } else {
                System.out.println("No data found for user ID: " + userID);
            }
        } else {
            System.out.println("Error retrieving user data for user ID: " + userID);
        }
        return null;
    }


    public boolean deleteUserData(String userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {userID};

        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();

        return deletedRows > 0;
    }


}
