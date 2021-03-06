package com.example.android_apprentice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import com.example.android_apprentice.MyDatabase;
import com.example.android_apprentice.MyDownload_Service;
import android.view.KeyEvent;


public class MainActivity extends AppCompatActivity {

    private MyDownload_Service.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Service","DownloadBinder intial");
            downloadBinder = (MyDownload_Service.DownloadBinder) service;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable display title on activity, but this method couldn't work if activity extends from AppCompatActivity.
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // Disable display title on activity, if you want to enable menu feature in the project, please don't hide title.
        //getSupportActionBar().hide();

        Intent intent = new Intent(this, MyDownload_Service.class);
        startService(intent); // ????????????
        bindService(intent, connection, BIND_AUTO_CREATE); // ????????????
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        }
        TextView mydisplay = findViewById(R.id.Display_textbox);
        String[] mystring = {"123","456"};
        mydisplay.setText(QLog_JNI.Qlog_Start(1)+QLog_JNI.Qlog_Start(2)+QLog_JNI.Qlog_Start(3));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        Log.d("Apprentice","onKeyDown:"+ keyCode + "->"+event.toString());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("Menu",item.getTitle().toString());
        MyDatabase phonebook_db = new MyDatabase(this, "myPhoneBook.db",null,1);


        switch (item.getItemId()) {
            case R.id.create_item:
                Toast.makeText(this, "You click Create item???",Toast.LENGTH_SHORT).show();
                //phonebook_db.getWritableDatabase();
                phonebook_db.onCreate(phonebook_db.getWritableDatabase());
                break;
            case R.id.add_item:
                Toast.makeText(this, "You click Add item???",Toast.LENGTH_SHORT).show();
                phonebook_db.phonebook_insert(phonebook_db.getWritableDatabase(),"Cheng","18665803207","Nanshan district,Shenzhen City");
                break;
            case R.id.update_item:
                Toast.makeText(this, "You click Update item???",Toast.LENGTH_SHORT).show();
                phonebook_db.phonebook_update(phonebook_db.getWritableDatabase());
                break;
            case R.id.delete_item:
                Toast.makeText(this, "You click Remove item???",Toast.LENGTH_SHORT).show();
                phonebook_db.phonebook_delete(phonebook_db.getWritableDatabase());
                break;
            case R.id.check_item:
                Toast.makeText(this, "You click Check item???",Toast.LENGTH_SHORT).show();
                phonebook_db.phonebook_check(phonebook_db.getWritableDatabase());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void open_second_activity(View view)
    {
        //This is the explicit intent method
        Intent myintent = new Intent(MainActivity.this, SecondActivity.class);
        //This is the implicit intent method
        //Intent myintent = new Intent("android.intent.action.SecAct");
        myintent.setData(Uri.parse("This is from first activity"));
        //startActivity(myintent);
        startActivityForResult(myintent,1);
        Log.d("Button","open second activity click");
    }

    public void bt_sendBroadcast_handler(View view)
    {
        Intent intent_bc = new Intent("com.example.android_apprentice.MY_BC");
        sendBroadcast(intent_bc,null);
        Log.d("Button","Send BroadCast");
    }
    public void bt_download_handler(View view)
    {
        Button bt_service = (Button)findViewById(R.id.bt_download);
        switch (bt_service.getText().toString())
        {
            case "Download":
                bt_service.setText("Pause");
                //String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                String url = "http://speed.hetzner.de/100MB.bin";
                downloadBinder.startDownload(url);
                break;
            case "Pause":
                bt_service.setText("Cancel");
                downloadBinder.pauseDownload();
                break;
            case "Cancel":
                bt_service.setText("Download");
                downloadBinder.cancelDownload();
                break;
            default:
                bt_service.setText("Download");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK)
        {
            String return_data = data.getData().toString();
            Log.d("MainActivity",return_data);

        }
        //super.onActivityResult(requestCode,resultCode,data);
    }
}