package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResiltActivity extends AppCompatActivity {

    TextView txtTotalCorrect,txtTotalWrong,txtTotalEmpty,txtTotal;

    Button btnPlay,btnExit;
    int correct;
    int wrong;
    int empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resilt);

        txtTotalCorrect=findViewById(R.id.txtTotalCorrect);
        txtTotalWrong=findViewById(R.id.txtTotalWrong);
        txtTotalEmpty=findViewById(R.id.txtTotalEmpty);
        txtTotal=findViewById(R.id.txtTotal);


        btnPlay=findViewById(R.id.btnPlay);
        btnExit=findViewById(R.id.btnExit);

        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);
        empty=getIntent().getIntExtra("empty",0);

        txtTotalCorrect.setText("Total Correct Answer: "+correct);

        txtTotalWrong.setText("Total Wrong Answer: "+wrong);

        txtTotalEmpty.setText("Total Empty Answer: "+empty);

        txtTotal.setText("Success Rate: "+(correct*10));



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ResiltActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_HOME);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                finish();
            }
        });



    }
}