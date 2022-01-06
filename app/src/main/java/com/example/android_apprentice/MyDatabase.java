package com.example.android_apprentice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;



public class MyDatabase extends SQLiteOpenHelper {
    public static final String Create_phonebook = "create table phonebook ("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"cell_number text,"
            +"address text)";
    //public static final String count_phonebook = "select count(*) from sqlite_master where type='table' and name='phonebook'"
    private Context mContext;


    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try
        {
            Cursor c = sqLiteDatabase.query("phonebook",null,null,null,null,null,null);
        }
        catch(Exception e)
        {
            sqLiteDatabase.execSQL(Create_phonebook);
            Toast.makeText(mContext, "Create phonebook database successfully", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void phonebook_insert(SQLiteDatabase sqLiteDatabase,String name, String cell_no, String add)
    {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("cell_number",cell_no);
        values.put("address",add);
        sqLiteDatabase.insert("phonebook",null,values);
    }

    public void phonebook_delete(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.delete("phonebook","name = ?",new String[]{"Cheng"});

    }

    public void phonebook_update(SQLiteDatabase sqLiteDatabase)
    {
        ContentValues values = new ContentValues();
        values.put("address","Nanshan district,Shenzhen City,Guangdong Province");
        sqLiteDatabase.update("phonebook",values,"name = ?",new String[]{"Cheng"});

    }

    public void phonebook_check(SQLiteDatabase sqLiteDatabase)
    {
        Cursor mycursor;
        mycursor = sqLiteDatabase.query("phonebook",null,null,null,null,null,null);
        if(!mycursor.moveToFirst())
        {
            Toast.makeText(mContext, "No data retrieve", Toast.LENGTH_SHORT).show();
            return;
        }

        do {
            Log.d("MainActivity","Name is" + mycursor.getString(mycursor.getColumnIndex("name")));
            Log.d("MainActivity","Cell number is" + mycursor.getString(mycursor.getColumnIndex("cell_number")));
            Log.d("MainActivity","address" + mycursor.getString(mycursor.getColumnIndex("address")));
        }while(mycursor.moveToNext());
    }
}
