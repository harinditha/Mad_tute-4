package com.example.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserMaster.Users.TABLE_NAME + " (" +
                        UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.Users.COLUMN_NAME_USERNAME + "TEXT," +
                        UserMaster.Users.COLUMN_NAME_PASSWORD + "TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo(String userName, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME, userName);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        long newRowID = db.insert(UserMaster.Users.TABLE_NAME, null, values);


        return newRowID;
    }

    public List readAllInfo() {
        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD};

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + "DESC";

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex(UserMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(UserMaster.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(password);
        }
        cursor.close();
        return userNames;

    }

        public boolean checkUser(String email, String password) {

            // array of columns to fetch
            String[] columns = {
                    UserMaster.Users._ID
            };
            SQLiteDatabase db = this.getReadableDatabase();
            // selection criteria
            String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " = ?" + " AND " + UserMaster.Users.COLUMN_NAME_PASSWORD + " = ?";

            // selection arguments
            String[] selectionArgs = {email, password};

            // query user table with conditions
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
             */
            Cursor cursor = db.query(UserMaster.Users.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order

            int cursorCount = cursor.getCount();

            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return true;
            }

            return false;
        }

        public void deleteInfo (String userName) {
        SQLiteDatabase db = getReadableDatabase();

        String Selection = UserMaster.Users.COLUMN_NAME_USERNAME + "Like ?";

        String [] selectionArgs = {userName};

        db.delete(UserMaster.Users.TABLE_NAME, Selection, selectionArgs);
    }

    public void updateInfo (String userName, String password) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        String Selection = UserMaster.Users.COLUMN_NAME_USERNAME + "Like ?";
        String [] selectionArgs = {userName};

        int count = db.update(
                UserMaster.Users.TABLE_NAME,
                values,
                Selection,
                selectionArgs

        );
    }



}






