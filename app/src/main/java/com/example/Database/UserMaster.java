package com.example.Database;

import android.provider.BaseColumns;

public class UserMaster {
    private UserMaster() {

    }


        public static class Users implements BaseColumns {

        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";


    }
}
