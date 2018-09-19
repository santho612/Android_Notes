package com.example.rpnaren.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView l;ArrayList<String> a;
int count;    String t;
    int[] co=new int[50];Intent i1;
    @Override
    protected void onStart() {
        super.onStart();
        display();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i=new Intent(this,viewnote.class);
        i.putExtra("count",count);
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         db=this.openOrCreateDatabase("notes",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notes(content VARCHAR(50),id INTEGER PRIMARY KEY)");
        l=(ListView) findViewById(R.id.l);
       display();
        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            t=a.get(i);
               alert();

                return false;
            }
        });
         i1=new Intent(this,view.class);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                t=a.get(i);

                i1.putExtra("content",t);
                startActivity(i1);
            }
        });
    }

    void display()
    {
        try{Cursor c=db.rawQuery("Select * from notes ",null);
         a=new ArrayList<>();
        c.moveToLast();
        count=c.getInt(1);
        while(!c.isBeforeFirst())
        {
            String s=c.getString(0);
            a.add(s);


            c.moveToPrevious();}


        }
        catch (Exception e)
        {}
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a);
        l.setAdapter(aa);

      }
void alert()
{
                    new AlertDialog.Builder(this)
                        .setIcon(android.R.mipmap.sym_def_app_icon).setTitle("Do you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.execSQL("DELETE FROM notes where content='"+t+"'");
                                display();
                            }
                        }).setNegativeButton("NO",null).show();

}


}
