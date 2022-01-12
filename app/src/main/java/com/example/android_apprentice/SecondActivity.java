package com.example.android_apprentice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView contactsView;
    ArrayAdapter<String> myadapter;

    List<String> contactList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       /* TextView mytext = findViewById(R.id.textView);
        Intent intent = getIntent();
        mytext.setText(intent.getData().toString());
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
        */
        contactsView = (ListView) findViewById(R.id.contacts_view);
        myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactList);
        contactsView.setAdapter(myadapter);
        readContacts();

    }

    public void bt_close_handler(View view)
    {
        Intent thisintent = new Intent();
        Log.d("SecondActivity","bt_close pressed");
        thisintent.setData(Uri.parse("Return from Second Activity"));
        setResult(RESULT_OK,thisintent);
        finish();

    }

    @Override public void onBackPressed()
    {
        Intent thisintent = new Intent();
        Log.d("SecondActivity","back button pressed");
        thisintent.setData(Uri.parse("Return from Second Activity"));
        setResult(RESULT_OK,thisintent);
        finish();
        readContacts();
    }

    private void readContacts(){
        Cursor mycursor = null;
        Log.d("SecondActivity","Read Contacts");
        try {
                mycursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,null,null,null);
                while(mycursor.moveToNext()){
                    String display_Name = mycursor.getString(mycursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String display_number = mycursor.getString(mycursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactList.add(display_Name+"\n"+display_number);
                }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

}