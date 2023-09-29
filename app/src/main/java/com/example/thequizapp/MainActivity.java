package com.example.thequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linear;
    TextView total_question,actual_questions;

    Button ans_a,ans_b,ans_C,ans_d,button_submit;

    int score=0;
    int total_questions=QuestionAnswer.question.length;
    int currentQuestion=0;

    String selected_ans="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total_question=findViewById(R.id.total_question);
        actual_questions=findViewById(R.id.actual_questions);
        linear=findViewById(R.id.linear);
        ans_a=findViewById(R.id.ans_a);
        ans_b=findViewById(R.id.ans_b);
        ans_C=findViewById(R.id.ans_C);
        ans_d=findViewById(R.id.ans_d);
        actual_questions=findViewById(R.id.actual_questions);
        button_submit=findViewById(R.id.button_submit);

        //Setting The Onclick listener

        ans_a.setOnClickListener(this);
        ans_b.setOnClickListener(this);
        ans_C.setOnClickListener(this);
        ans_d.setOnClickListener(this);
        button_submit.setOnClickListener(this);

        total_question.setText("Total Questions :- "+total_question);

        loadingnextque();
    }

    private void loadingnextque() {

        if(currentQuestion==total_questions){
            finishQuiz();
            return;
        }

        total_question.setText(QuestionAnswer.question[currentQuestion]);
        ans_a.setText(QuestionAnswer.choices[currentQuestion][0]);
        ans_b.setText(QuestionAnswer.choices[currentQuestion][1]);
        ans_C.setText(QuestionAnswer.choices[currentQuestion][2]);
        ans_d.setText(QuestionAnswer.choices[currentQuestion][3]);

    }

    private void finishQuiz() {
        String passStatus="";
        if(score>total_questions*0.60)
        {
            passStatus  =" Passed ";
        }else {
            passStatus=" Failed ";
        }

        new AlertDialog.Builder(this).setTitle(passStatus).
                setMessage("Score is "+ score+" Out of "+total_questions).
                setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz())).
                setCancelable(false).show();


    }

    private void restartQuiz() {
        score=0;
        currentQuestion=0;
        loadingnextque();

    }

    @Override
    public void onClick(View view) {

        ans_a.setBackgroundColor(Color.rgb(255, 193, 7));
       ans_b.setBackgroundColor(Color.rgb(255, 193, 7));
        ans_C.setBackgroundColor(Color.rgb(255, 193, 7));
        ans_d.setBackgroundColor(Color.rgb(255, 193, 7));

        Button clickedButton=(Button) view;

        if(clickedButton.getId()==R.id.button_submit){
            //Only Submit button

            if(selected_ans.equals(QuestionAnswer.correctAnswer[currentQuestion])){
                score++;
            }

            currentQuestion++;
            loadingnextque();



        }else{//Only Choices Button

            selected_ans=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);

        }

    }



}