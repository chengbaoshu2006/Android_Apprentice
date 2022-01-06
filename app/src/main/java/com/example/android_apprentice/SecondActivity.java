package com.example.android_apprentice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView mytext = findViewById(R.id.textView);
        Intent intent = getIntent();
        mytext.setText(intent.getData().toString());
        intent = new Intent(Intent.ACTION_VIEW);


        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
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
    }
}