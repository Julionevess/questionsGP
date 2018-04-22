package com.jns.questoesgp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.model.Question;
import com.jns.questoesgp.questoesgp.R;
import com.jns.questoesgp.util.SharedPreferenceUtil;
import com.jns.questoesgp.util.Util;

import java.util.List;

public class LastQuestionActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String CURRENT_PAGE = "currentPage";
    private static final String CORRECT = "Certo";
    private static final String WRONG = "Errado";

    public static List<Answer> answers;
    public static List<Question> questions;
    public static List<String> options;
    public static Context context;
    public static Question questionSelected;
    public static int currentPage;

    Button btnCorrect;

    /*
     * Correct Questions
     */
    public static void correctQuestions(){

        for (int i = 0; i < questions.size() ; i++) {
            if (questions.get(i).getAnswer().equals(answers.get(i).getAnswer())){
                answers.get(i).setSituation(CORRECT);
            }else{
                answers.get(i).setSituation(WRONG);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firts_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();



        questions = SharedPreferenceUtil.getListQuestion(context);
        if (questions.size() > 0) {
            questionSelected = questions.get(0);
            answers = SharedPreferenceUtil.getListAnswers(context);
        }


        options = Util.unsortedList(questionSelected);

        TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        RadioButton rbOptionOne = (RadioButton) findViewById((R.id.rbOptionOne));
        RadioButton rbOptionTwo = (RadioButton) findViewById((R.id.rbOptionTwo));
        RadioButton rbOptionThree = (RadioButton) findViewById((R.id.rbOptionThree));
        RadioButton rbOptionFour = (RadioButton) findViewById((R.id.rbOptionFour));
        RadioButton rbOptionFive = (RadioButton) findViewById((R.id.rbOptionFive));

        tvQuestion.setText(questionSelected.getQuestion());
        rbOptionOne.setText(options.get(0));
        rbOptionTwo.setText(options.get(1));
        rbOptionThree.setText(options.get(2));
        rbOptionFour.setText(options.get(3));
        rbOptionFive.setText(options.get(4));

        btnCorrect = (Button) findViewById(R.id.btnNext);


    }

    private void init() {
        context = this;
        Bundle b = getIntent().getExtras();
        currentPage = -1;
        if(b != null) {
            currentPage = b.getInt(CURRENT_PAGE);
            currentPage++;
        }
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

        if (v.equals(btnCorrect)){
            correctQuestions();
        }
    }
}
