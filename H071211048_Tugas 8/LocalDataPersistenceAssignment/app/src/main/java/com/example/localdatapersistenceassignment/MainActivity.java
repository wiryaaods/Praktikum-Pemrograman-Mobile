package com.example.localdatapersistenceassignment;

import static com.example.localdatapersistenceassignment.AddNotes.EXTRA_NOTES;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.localdatapersistenceassignment.adapter.NotesAdapter;
import com.example.localdatapersistenceassignment.helper.MappingHelper;
import com.example.localdatapersistenceassignment.helper.NotesHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ImageView btn;
    private TextView tv_addNotes;
    private Notes notes;
    private SearchView searchView;
    private RecyclerView rv;
    private ArrayList<Notes> notesArrayList;
    private NotesHelper notesHelper;
    private NotesAdapter notesAdapter;
    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getData() != null) {
                            switch (result.getResultCode()) {
                                case AddNotes.RESULT_ADD :
                                    notes = result.getData().getParcelableExtra("EXTRA_NOTES");
                                    showCurrentNotes(notes);
                                    Toast.makeText(this, "Item added successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                case AddNotes.RESULT_UPDATE :
                                    notes = result.getData().getParcelableExtra(AddNotes.EXTRA_NOTES);
                                    showCurrentNotes(notes);
                                    Toast.makeText(this, "Item edited successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                case AddNotes.RESULT_DELETE :
                                    Toast.makeText(this, "Item removed successfully",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        searchView = findViewById(R.id.searchView);
        btn = findViewById(R.id.btn_add);
        rv = findViewById(R.id.rv_notes);

        notesHelper = NotesHelper.getInstance(getApplicationContext());
        notesHelper.open();

        notesArrayList = new ArrayList<>();
        notesAdapter = new NotesAdapter( notesArrayList, note -> {
            Intent intent = new Intent(MainActivity.this, AddNotes.class);
            intent.putExtra(EXTRA_NOTES, note);
            resultLauncher.launch(intent);
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(notesAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNote(newText);
                return true;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNotes = new Intent(MainActivity.this, AddNotes.class);
                resultLauncher.launch(addNotes);
            }
        });
    }

    private void loadNotes(){
        new LoadNotesAsync(this, notes1 -> {
            runOnUiThread(() ->{
                notesArrayList.clear();
                notesArrayList.addAll(notes1);
                notesAdapter.notifyDataSetChanged();
                if (notes1.size() > 0) {
                    notes = notes1.get(0);
                }else{
                    notes = null;
                }
                showCurrentNotes(notes);
            });
        }).execute();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadNotes();
    }

    private void searchNote(CharSequence charSequence) {
        rv.setVisibility(View.GONE);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }

            String query = charSequence.toString().toLowerCase().trim();
            ArrayList<Notes> searchNote = new ArrayList<>();

            if (!TextUtils.isEmpty(query)){
                for (Notes note : notesArrayList){
                    String title = note.getTitle().toLowerCase();
                    if (title.contains(query)){
                        searchNote.add(note);
                    }
                }
            }else{
                loadNotes();
            }

            handler.post(() -> {
                notesAdapter.updateData(searchNote);
                rv.setVisibility(View.VISIBLE);
            });
        });
    }

    private void showCurrentNotes (Notes notes){
        tv_addNotes = findViewById(R.id.tv_addNotes);
        if (notes != null) {
            tv_addNotes.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
        } else {
            tv_addNotes.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.GONE);
        }

    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<Notes>> {
        private final WeakReference<Context> weakContext;
        private final LoadNotesCallback callback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            this.callback = callback;
        }

        @Override
        protected ArrayList<Notes> doInBackground(Void... voids) {
            Context context = weakContext.get();
            NotesHelper noteHelper = NotesHelper.getInstance(context);
            noteHelper.open();
            Cursor notesCursor = noteHelper.queryAll();
            ArrayList<Notes> notes = MappingHelper.mapCursorToArrayList(notesCursor);
            noteHelper.close();
            return notes;
        }
        @Override
        protected void onPostExecute(ArrayList<Notes> notes) {
            callback.postExecute(notes);
        }
    }

        interface LoadNotesCallback {
        void postExecute(ArrayList<Notes> notes);
    }

}