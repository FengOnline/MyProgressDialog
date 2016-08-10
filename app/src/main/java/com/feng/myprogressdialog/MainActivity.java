package com.feng.myprogressdialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.feng.myprogresslibrary.MyProgressDialog;

public class MainActivity extends AppCompatActivity {

    Button btn;
    MyProgressDialog myProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.start);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myProgressDialog=new MyProgressDialog(MainActivity.this);
                myProgressDialog.setColor(Color.RED);
                myProgressDialog.setDuration(1000);
                myProgressDialog.setMaxRadius(20);
                myProgressDialog.setCancelable(true);
                myProgressDialog.show();
            }
        });
    }
}
