package com.jns.questoesgp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.model.Question;
import com.jns.questoesgp.questoesgp.R;
import com.jns.questoesgp.util.SharedPreferenceUtil;
import com.jns.questoesgp.util.Util;

import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    public static List<Answer> answers;
    public static List<Question> questions;
    public static List<String> options;
    public static Context context;
    public static Question questionSelected;
    public static int currentPage;
    private Button btnNext;
    private Button btnBack;
    private RadioButton rbOptionOne;
    private RadioButton rbOptionTwo;
    private RadioButton rbOptionThree;
    private RadioButton rbOptionFour;
    private RadioButton rbOptionFive;
    private RadioGroup rgOptions;
    public static final String CURRENT_PAGE = "currentPage";
    public Answer answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firts_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        rbOptionOne = (RadioButton) findViewById((R.id.rbOptionOne));
        rbOptionTwo = (RadioButton) findViewById((R.id.rbOptionTwo));
        rbOptionThree = (RadioButton) findViewById((R.id.rbOptionThree));
        rbOptionFour = (RadioButton) findViewById((R.id.rbOptionFour));
        rbOptionFive = (RadioButton) findViewById((R.id.rbOptionFive));

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

        options = Util.unsortedList(questionSelected);

        tvQuestion.setText(questionSelected.getQuestion());
        rbOptionOne.setText(options.get(0));
        rbOptionTwo.setText(options.get(1));
        rbOptionThree.setText(options.get(2));
        rbOptionFour.setText(options.get(3));
        rbOptionFive.setText(options.get(4));

        btnNext = (Button) findViewById(R.id.btnNext);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);

    }

    private void init() {
        context = this;
        Bundle b = getIntent().getExtras();
        currentPage = -1;
        if(b != null) {
            currentPage = b.getInt(CURRENT_PAGE);
            currentPage++;
        }
        questions = SharedPreferenceUtil.getListQuestion(context);
        if (questions.size() > 0) {
            questionSelected = questions.get(0);
            answers = SharedPreferenceUtil.getListAnswers(context);
        }
        answer = new Answer();
        answer.setQuestion(questions.get(currentPage).getQuestion());
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
            if (v.getId() == btnNext.getId()) { if (answers.size() > currentPage) {
                answers.add(currentPage, answer);
            }else{
                answers.add(answer);
            }
            SharedPreferenceUtil.setListAnswer(context, answers);
            Intent intent = new Intent(context, LastQuestionActivity.class);
            Bundle b = new Bundle();

            b.putInt(CURRENT_PAGE, currentPage);
            intent.putExtras(b);
            startActivity(intent);
            finish();
            startActivity(intent);
        }else if (v.getId() == btnBack.getId()){
            super.onBackPressed();
        }
    }
}
