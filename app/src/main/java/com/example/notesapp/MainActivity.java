package com.example.notesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recview;
    FloatingActionButton fBtnAdd;
    SearchView searchView;
    MyAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        searchView.setQueryHint("Search items...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        fBtnAdd = findViewById(R.id.floatingActionButtonAdd);
        fBtnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,addnote_activity.class);
            startActivity(intent);
        });
        fetchData();
    }

    private void fetchData() {
        recview = findViewById(R.id.recview);

        AppDatabase appDatabase = AppDatabase.getDB(this);
        List<User> list = appDatabase.userDao().getAll();
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recview.setLayoutManager(layoutManager);
        adapter = new MyAdapter(list);
        recview.setAdapter(adapter);
    }
}