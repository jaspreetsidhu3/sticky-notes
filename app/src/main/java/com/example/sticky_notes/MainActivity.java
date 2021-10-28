package com.example.sticky_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sticky_notes.DataLayer.Data;

public class MainActivity extends AppCompatActivity {
    private EditText edtNote;
    private Button btnSave;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNote = findViewById(R.id.edtNote);
        btnSave = findViewById(R.id.save);
        data = new Data(getApplicationContext());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

