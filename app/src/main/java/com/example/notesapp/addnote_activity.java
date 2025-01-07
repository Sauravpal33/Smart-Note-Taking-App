package com.example.notesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addnote_activity extends AppCompatActivity {

    EditText et1, et2;
    FloatingActionButton fBtnCheck;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addnote);

        fBtnCheck = findViewById(R.id.floatingActionButtonCheck);
        et1 = findViewById(R.id.addTitle);
        et2 = findViewById(R.id.addDesc);

        AppDatabase appDatabase = AppDatabase.getDB(this);

        fBtnCheck.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String title = et1.getText().toString();
                String desc = et2.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(addnote_activity.this, "Enter the Title", Toast.LENGTH_SHORT).show();
                } else if (desc.isEmpty()) {
                    Toast.makeText(addnote_activity.this, "Enter Description", Toast.LENGTH_SHORT).show();
                } else {
                    appDatabase.userDao().insertAll(new User(title, desc));
                    Toast.makeText(addnote_activity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addnote_activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}