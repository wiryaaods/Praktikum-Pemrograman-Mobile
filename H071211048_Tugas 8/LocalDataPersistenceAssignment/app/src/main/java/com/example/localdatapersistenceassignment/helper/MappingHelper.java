package com.example.localdatapersistenceassignment.helper;

import android.database.Cursor;

import com.example.localdatapersistenceassignment.DatabaseContract;
import com.example.localdatapersistenceassignment.Notes;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<Notes> mapCursorToArrayList (Cursor cursor){
        ArrayList<Notes> note = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.DESCRIPTION));
            String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotesColumns.TIMESTAMP));
            note.add(new Notes(id, title, description, timestamp));
        }
        return note;
    }

}
