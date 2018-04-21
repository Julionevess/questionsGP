package com.jns.questoesgp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
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

    public static List<Answer> answers;
    public static List<Question> questions;
    public static List<String> options;

    public static Context context;
    public static Question questionOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firts_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        questions = SharedPreferenceUtil.getListQuestion(context);
        if (questions.size() > 0) {
            questionOne = questions.get(0);
            answers = SharedPreferenceUtil.getListAnswers(context);
        }


        options = Util.unsortedList(questionOne);

        TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        RadioButton rbOptionOne = (RadioButton) findViewById((R.id.rbOptionOne));
        RadioButton rbOptionTwo = (RadioButton) findViewById((R.id.rbOptionTwo));
        RadioButton rbOptionThree = (RadioButton) findViewById((R.id.rbOptionThree));
        RadioButton rbOptionFour = (RadioButton) findViewById((R.id.rbOptionFour));
        RadioButton rbOptionFive = (RadioButton) findViewById((R.id.rbOptionFive));

        tvQuestion.setText(questionOne.getQuestion());
        rbOptionOne.setText(options.get(0));
        rbOptionTwo.setText(options.get(1));
        rbOptionThree.setText(options.get(2));
        rbOptionFour.setText(options.get(3));
        rbOptionFive.setText(options.get(4));

        List<Question> questions = SharedPreferenceUtil.getListQuestion(this);



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

    }
}
