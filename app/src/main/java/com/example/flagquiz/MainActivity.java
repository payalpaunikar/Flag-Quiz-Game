package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private TextView txtCorrect,txtWrong,txtEmpty,txtQuestion;

    private ImageView imgFlag,imgNext;
    private Button btn1,btn2,btn3,btn4;

    private FlagsDatabase fdatabase;

    private ArrayList<FlagsModel> questionList;

    int correct=0;
    int wrong=0;
    int empty=0;
    int question=0;

    private FlagsModel correctFlag;

    private ArrayList<FlagsModel> wrongOptionsList;

    //to set ramdomly question
    HashSet<FlagsModel> mixOptions = new HashSet<>();

    ArrayList<FlagsModel> options = new ArrayList<>();

    boolean isbtnClick=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtCorrect=findViewById(R.id.txtCorrect);
        txtEmpty=findViewById(R.id.txtEmpty);
        txtWrong=findViewById(R.id.txtWrong);
        txtQuestion=findViewById(R.id.txtQuestion);

        imgFlag=findViewById(R.id.imgFlag);
        imgNext=findViewById(R.id.imgNext);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);


        fdatabase=new FlagsDatabase(MainActivity.this);

        questionList=new FlagsDAO().getRandomTenquestion(fdatabase);

        loadQuestions();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerControl(btn1);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(btn2);


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(btn3);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerControl(btn4);

            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question++;
                if(!isbtnClick && question<10)
                {
                    empty++;
                    txtEmpty.setText("Empty : "+empty);
                    loadQuestions();
                }
                else if(isbtnClick && question<10)
                {
                    loadQuestions();

                    btn1.setClickable(true);
                    btn2.setClickable(true);
                    btn3.setClickable(true);
                    btn4.setClickable(true);

                    btn1.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    btn2.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    btn3.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    btn4.setBackgroundColor(getResources().getColor(R.color.purple_500));
                }

                else if(question==10)
                {
                    Intent i=new Intent(MainActivity.this,ResiltActivity.class);
                    i.putExtra("correct",correct);
                    i.putExtra("wrong",wrong);
                    i.putExtra("empty",empty);
                    startActivity(i);
                    finish();
                }

                isbtnClick=false;

            }
        });


    }

    public  void loadQuestions()
    {
        txtQuestion.setText("Question : "+(question+1));

        correctFlag=questionList.get(question);

        imgFlag.setImageResource(getResources().getIdentifier(correctFlag.getFlag_image(),"drawable",getPackageName()));

        wrongOptionsList =new FlagsDAO().getRandomThreeOptins(fdatabase,correctFlag.getFlag_id());

        mixOptions.clear();
        mixOptions.add(correctFlag);
        mixOptions.add(wrongOptionsList.get(0));
        mixOptions.add(wrongOptionsList.get(1));
        mixOptions.add(wrongOptionsList.get(2));

       options.clear();
       for (FlagsModel fig:mixOptions)
       {
           options.add(fig);
       }

       btn1.setText(options.get(0).getFlag_name());
       btn2.setText(options.get(1).getFlag_name());
       btn3.setText(options.get(2).getFlag_name());
       btn4.setText(options.get(3).getFlag_name());

    }

    @SuppressLint("ResourceAsColor")
    public  void answerControl(Button button)
    {
        String buttonText = button.getText().toString();
        String correctAnswer = correctFlag.getFlag_name();

        if(correctAnswer==buttonText)
        {
            button.setBackgroundColor(Color.GREEN);
            correct++;
        }
        else
        {
            wrong++;
            button.setBackgroundColor(Color.RED);

            if (btn1.getText().toString().equals(correctAnswer))
            {
                btn1.setBackgroundColor(Color.GREEN);
            }
            if (btn2.getText().toString().equals(correctAnswer))
            {
                btn2.setBackgroundColor(Color.GREEN);
            }
            if (btn3.getText().toString().equals(correctAnswer))
            {
                btn3.setBackgroundColor(Color.GREEN);
            }
            if (btn4.getText().toString().equals(correctAnswer))
            {
                btn4.setBackgroundColor(Color.GREEN);
            }

        }
        btn1.setClickable(false);
        btn2.setClickable(false);
        btn3.setClickable(false);
        btn4.setClickable(false);

        txtCorrect.setText("Correct : "+correct);

        txtWrong.setText("Wrong : "+wrong);

        isbtnClick=true;

    }
}