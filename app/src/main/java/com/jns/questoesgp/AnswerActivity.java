package com.jns.questoesgp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jns.questoesgp.adapter.AnswerAdapter;
import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.model.Question;
import com.jns.questoesgp.questoesgp.R;
import com.jns.questoesgp.util.SharedPreferenceUtil;
import com.jns.questoesgp.util.Util;

import java.util.List;

public class AnswerActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    private AnswerAdapter adapter;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private View rootView;


    public static final String CURRENT_PAGE = "currentPage";
    private static final String CORRECT = "Certo";
    private static final String WRONG = "Errado";

    public static List<Answer> answers;
    public static List<Question> questions;
    public static Context context;
    public static int currentPage;
    public Answer answer;

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
        setContentView(R.layout.activity_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        adapter = new AnswerAdapter(this);
        adapter.setItems(answers);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.swapAdapter(adapter, true);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);




        /*
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

        btnCorrect = (Button) findViewById(R.id.btnNext);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnCorrect = (Button) findViewById(R.id.btnNext);

*/
    }

    private void init() {

        context = this;
        Bundle b = getIntent().getExtras();
        currentPage = -1;
        if(b != null) {
            currentPage = b.getInt(CURRENT_PAGE);
            currentPage++;
        }
        answers = SharedPreferenceUtil.getListAnswers(context);

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
    public void onRefresh() {
        //TODO implementar
        swipeRefreshLayout.setRefreshing(false);
    }
}
