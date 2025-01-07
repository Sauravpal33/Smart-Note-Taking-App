package com.example.notesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class update_activity extends AppCompatActivity {

    EditText ut1, ut2;
    int uid;
    FloatingActionButton dfBtn;
    FloatingActionButton ufBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        ut1 = findViewById(R.id.updateTitle);
        ut2 = findViewById(R.id.updateDesc);
        ufBtn = findViewById(R.id.floatingActionButtonUpdate);
        dfBtn = findViewById(R.id.floatingActionButtonDelete);


        ut1.setText(getIntent().getStringExtra("itemTitle"));
        ut2.setText(getIntent().getStringExtra("itemDesc"));
        uid = Integer.parseInt(getIntent().getStringExtra("itemId"));

        ufBtn.setOnClickListener(view -> {
            AppDatabase appDatabase = AppDatabase.getDB(getApplicationContext());
            appDatabase.userDao().update(new User(uid, ut1.getText().toString(), ut2.getText().toString()));
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });

        dfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog sheetDialog = new BottomSheetDialog(update_activity.this);
                sheetDialog.setContentView(R.layout.dailog_box);
                sheetDialog.show();

                Button yes, no;
                yes = sheetDialog.findViewById(R.id.buttonYes);
                no = sheetDialog.findViewById(R.id.buttonNo);

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sheetDialog.dismiss();
                    }
                });
                yes.setOnClickListener(view12 -> {
                    AppDatabase appDatabase = AppDatabase.getDB(getApplicationContext());
                    appDatabase.userDao().delete(new User(uid, ut1.getText().toString(), ut2.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                });
            }
        });
    }
}
