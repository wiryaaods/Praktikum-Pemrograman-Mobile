package com.example.localdatapersistenceassignment.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.localdatapersistenceassignment.DatabaseContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_NOTES =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TIMESTAMP DEFAULT (DATETIME('NOW', 'LOCALTIME')))",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.NotesColumns._ID,
                    DatabaseContract.NotesColumns.TITLE,
                    DatabaseContract.NotesColumns.DESCRIPTION,
                    DatabaseContract.NotesColumns.TIMESTAMP
            );

    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
