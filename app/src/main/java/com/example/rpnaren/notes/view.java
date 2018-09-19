package com.example.rpnaren.notes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class view extends Activity {
    SQLiteDatabase db;String i;int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent i1=getIntent();
        i=i1.getStringExtra("content");
        db=this.openOrCreateDatabase("notes",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notes(content VARCHAR(50),id INTEGER PRIMARY KEY)");
        Cursor c=db.rawQuery("Select * from notes where content='"+i+"'",null);
        c.moveToFirst();
        count=c.getInt(1);
        EditText e=(EditText) findViewById(R.id.e1);
        e.setText(i);
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                db.execSQL("Update notes set content='"+editable.toString()+"' where id="+count);


            }
        });
    }
}
