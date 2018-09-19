package com.example.rpnaren.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class viewnote extends AppCompatActivity {
    SQLiteDatabase db;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnote);
        db=this.openOrCreateDatabase("notes",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notes(content VARCHAR(50),id INTEGER PRIMARY KEY)");
        db.execSQL("INSERT into notes(content) values('"+null+"')");
        EditText e1=(EditText) findViewById(R.id.e1);
        Intent i=getIntent();
        count=i.getIntExtra("count",0);
        count++;
        e1.addTextChangedListener(new TextWatcher() {
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
