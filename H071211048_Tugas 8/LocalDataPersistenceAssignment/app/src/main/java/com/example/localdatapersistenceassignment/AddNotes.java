package com.example.localdatapersistenceassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.localdatapersistenceassignment.helper.NotesHelper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNotes extends AppCompatActivity {

    public static final String EXTRA_NOTES = "extra_notes";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private NotesHelper notesHelper;
    private Notes notes;
    private EditText et_title, et_description;
    private Button btn_submit, btn_delete;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        getSupportActionBar().hide();

        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        btn_submit = findViewById(R.id.btn_submit);
        btn_delete = findViewById(R.id.btn_delete);

        notesHelper = NotesHelper.getInstance(getApplicationContext());
        notesHelper.open();
        notes = getIntent().getParcelableExtra(EXTRA_NOTES);
        if (notes !=null){
            isEdit = true;
        }else{
            notes = new Notes();
        }
        String buttonTitle;
        if (isEdit) {
            buttonTitle = "Update";
            if (notes != null) {
                et_title.setText(notes.getTitle());
                et_description.setText(notes.getDescription());
            }
            btn_delete.setVisibility(View.VISIBLE);
        } else {
            buttonTitle = "Submit";
        }
        btn_submit.setText(buttonTitle);
        btn_submit.setOnClickListener(view -> submit());
        btn_delete.setOnClickListener(view -> delete());
    }

    private void submit() {
        String title = et_title.getText().toString().trim();
        String description = et_description.getText().toString().trim();
        if(title.isEmpty()){
            et_title.setError("field can not be blank");
            return;
        }
        if(description.isEmpty()){
            et_description.setError("field can not be blank");
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        notes.setTitle(title);
        notes.setDescription(description);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTES, notes);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.NotesColumns.TITLE, title);
        if (isEdit) {
            values.put(DatabaseContract.NotesColumns.TIMESTAMP, "Edited at " + currentDate);
        } else {
            values.put(DatabaseContract.NotesColumns.TIMESTAMP, "Created at " + currentDate);
        }

        values.put(DatabaseContract.NotesColumns.DESCRIPTION, description);
        if(isEdit){
            long result = notesHelper.update(String.valueOf(notes.getId()), values);
            if(result > 0){
                setResult(RESULT_UPDATE, intent);
                finish();
            }else{
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        }else{
            long result = notesHelper.insert(values);
            if(result > 0){
                notes.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            }else{
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete(){
        long result = notesHelper.deleteById(String.valueOf(notes.getId()));
        if(result > 0){
            Intent intent = new Intent();
            intent.putExtra(EXTRA_NOTES, notes);
            setResult(RESULT_DELETE, intent);
            finish();
        }else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }
}