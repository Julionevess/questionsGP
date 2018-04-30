package com.jns.questoesgp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.model.Question;
import com.jns.questoesgp.questoesgp.R;
import com.jns.questoesgp.util.SharedPreferenceUtil;
import com.jns.questoesgp.util.Util;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.jns.questoesgp.MainActivity.context;

public class FirstQuestionActivity extends AppCompatActivity implements View.OnClickListener{

    public List<Answer> answers;
    public List<Question> questions;
    public int currentPage;
    public Context context;
    public Question questionOne;
    public List<String> options;
    private RadioButton rbOptionOne;
    private RadioButton rbOptionTwo;
    private RadioButton rbOptionThree;
    private RadioButton rbOptionFour;
    private RadioButton rbOptionFive;
    private RadioGroup rgOptions;
    TextView tvQuestion;
    public static final String CURRENT_PAGE = "currentPage";
    Answer answer;

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firts_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        rbOptionOne = (RadioButton) findViewById((R.id.rbOptionOne));
        rbOptionTwo = (RadioButton) findViewById((R.id.rbOptionTwo));
        rbOptionThree = (RadioButton) findViewById((R.id.rbOptionThree));
        rbOptionFour = (RadioButton) findViewById((R.id.rbOptionFour));
        rbOptionFive = (RadioButton) findViewById((R.id.rbOptionFive));

        TextView tvQuestionNumber = (TextView) findViewById(R.id.tvQuestionNumber);
        tvQuestionNumber.setText(tvQuestionNumber.getText().toString() + (currentPage + 1));


        rgOptions = (RadioGroup) findViewById(R.id.rgOptions);

        rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbOptionOne.getId()){
                    answer.setAnswer(rbOptionOne.getText().toString());
                }else if (checkedId == rbOptionTwo.getId()){
                    answer.setAnswer(rbOptionTwo.getText().toString());
                }else if (checkedId == rbOptionThree.getId()){
                    answer.setAnswer(rbOptionThree.getText().toString());
                }else if (checkedId == rbOptionFour.getId()){
                    answer.setAnswer(rbOptionFour.getText().toString());
                }else if (checkedId == rbOptionFive.getId()){
                    answer.setAnswer(rbOptionFive.getText().toString());
                }
            }
        });

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        options = Util.unsortedList(questionOne);
        tvQuestion.setText(questionOne.getQuestion());
        rbOptionOne.setText(options.get(0));
        rbOptionTwo.setText(options.get(1));
        rbOptionThree.setText(options.get(2));
        rbOptionFour.setText(options.get(3));
        rbOptionFive.setText(options.get(4));

    }

    private void init() {
        context = this;
        currentPage = 0;
        questions = SharedPreferenceUtil.getListQuestion(context);
        if (questions.size() > 0) {
            questionOne = questions.get(currentPage);
            answers = new ArrayList<Answer>();
        }
        answer = new Answer();
        answer.setQuestion(questions.get(currentPage).getQuestion());
        answer.setCorrectAnswer(questions.get(currentPage).getAnswer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == (btnNext.getId())) {
            if (answers.size() > currentPage) {
                answers.add(currentPage, answer);
            }else{
                answers.add(answer);
            }
            SharedPreferenceUtil.setListAnswer(context, answers);

            Intent intent = new Intent(context, QuestionActivity.class);
            Bundle b = new Bundle();
            b.putInt(CURRENT_PAGE, currentPage);
            intent.putExtras(b);
            startActivity(intent);
        }

    }
}
