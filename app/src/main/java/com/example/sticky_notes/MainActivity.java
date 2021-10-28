package com.example.sticky_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.sticky_notes.DataLayer.Data;

import java.io.IOException;

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
        setNote();
    }

    public void setNote() {
        String noteTxt = data.getNotes();
        if (!noteTxt.isEmpty()) {
            edtNote.setText(noteTxt);
        }
    }

    public void btnSave(View view) {
        String noteTxt = edtNote.getText().toString();
        if (TextUtils.isEmpty(noteTxt)) {
            Toast.makeText(getApplicationContext(), "Please enter note", Toast.LENGTH_LONG).show();
        }
        try {
            data.setNotes(noteTxt);
            updateWidget();
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateWidget() {
        if(!data.getNotes().isEmpty()){
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
            RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_layout);
            ComponentName widget = new ComponentName(getApplicationContext(), AppWidget.class);
            remoteViews.setTextViewText(R.id.idTVWidget, data.getNotes());
            appWidgetManager.updateAppWidget(widget, remoteViews);
        }
    }
}

