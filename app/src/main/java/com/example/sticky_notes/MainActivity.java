package com.example.sticky_notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.DialogInterface;
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
        else{
            try {
                data.setNotes(noteTxt);
                updateWidget(false);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateWidget(boolean isDeleteCall) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_layout);
        ComponentName widget = new ComponentName(getApplicationContext(), AppWidget.class);
        if(!isDeleteCall){
            remoteViews.setTextViewText(R.id.idTVWidget, data.getNotes());
        }
        else{
            remoteViews.setTextViewText(R.id.idTVWidget, "Click to start making note");
        }
        appWidgetManager.updateAppWidget(widget, remoteViews);
    }

    public void btnDelete(View view) {
        deleteNotesConfirmationBox();
    }

    public void deleteNotesConfirmationBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Delete Notes");
        builder.setMessage("Are you sure to delete all your notes?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            data.deleteNotes();
                            updateWidget(true);
                            edtNote.setText("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

