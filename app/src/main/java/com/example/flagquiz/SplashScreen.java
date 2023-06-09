package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

       btnStart=findViewById(R.id.btnStart);

        copyDatabase();

       btnStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(SplashScreen.this,MainActivity.class);
                 startActivity(i);
           }
       });



    }

    public void copyDatabase()
    {
        try {

            DatabaseCopyHelper helper=new DatabaseCopyHelper(SplashScreen.this);
            helper.createDataBase();
            helper.openDataBase();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}