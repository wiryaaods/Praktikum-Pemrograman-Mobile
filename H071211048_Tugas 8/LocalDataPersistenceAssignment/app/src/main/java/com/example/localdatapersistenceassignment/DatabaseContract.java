package com.example.localdatapersistenceassignment;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "notes";
    public static final class NotesColumns implements BaseColumns{
        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static final String TIMESTAMP = "timestamp";
    }
}
