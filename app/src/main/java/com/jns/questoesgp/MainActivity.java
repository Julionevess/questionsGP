package com.jns.questoesgp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.model.Question;
import com.jns.questoesgp.questoesgp.R;
import com.jns.questoesgp.util.SharedPreferenceUtil;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {




    private static final String CATEGORY_INTEGRATION = "Integração";
    private static final String CATEGORY_SCOPE = "Escopo";
    private static final String CATEGORY_COSTE = "Custo";
    private static final String CATEGORY_QUALITY = "Qualidade";
    private static final String CATEGORY_ACQUISITION = "Aquisição";
    private static final String CATEGORY_RESOURCE = "Recurso";
    private static final String CATEGORY_COMMUNICATION = "Comunicação";
    private static final String CATEGORY_RISK = "Risco";
    private static final String CATEGORY_SCHEDULE = "Cronograma";
    private static final String CATEGORY_STAKEHOLDER = "Partes interessadas";


    public static Context context;

    public static int totalQuestionSelected = 10;
    public static List<Answer> answers;
    public static List<Question> questionsSelected;
    public static List<Question> questionsAll;
    public static List<Question> questionsByCategory;
    public static String Category;


    /*
     * Catch all json questions
     */

    public static void catchAllQuestions(Context context) {
        questionsAll = new ArrayList<Question>();
        JsonReader reader;
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.questions);
            reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new Gson();
            Type usuariosListType = new TypeToken<ArrayList<Question>>(){}.getType();
            questionsAll = gson.fromJson(reader, usuariosListType);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /*
     * Select questions to play
     */
    public static void catchSelectedQuestions(){
        catchAllQuestions(context);
        questionsSelected = new ArrayList<Question>();
        Random r = new Random();
        for (int i = 0 ; i < totalQuestionSelected ; i++){
            questionsSelected.add(questionsAll.get(r.nextInt(totalQuestionSelected)));
        }
    }

    /*
     * Select question by category to play
     */
    public static void catchSelectedQuestionsByCategory(String category){
        catchAllQuestions(context);
        questionsSelected = new ArrayList<Question>();
        Random r = new Random();
        for (int i = 0 ; i < totalQuestionSelected ; i++){

            Question categorizedQuestion = questionsAll.get(r.nextInt(totalQuestionSelected));
            if (categorizedQuestion.getCategory().equals(category)){
                questionsSelected.add(categorizedQuestion);
            }
        }
        SharedPreferenceUtil.setListQuestion(context, questionsSelected);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        CardView cardIntegration = (CardView) findViewById(R.id.cardIntegration);
        CardView cardScope = (CardView) findViewById(R.id.cardScope);

        cardIntegration.setOnClickListener(this);
        cardScope.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardIntegration) {
            catchSelectedQuestionsByCategory(CATEGORY_INTEGRATION);
        } else if (v.getId() == R.id.cardScope) {
            catchSelectedQuestionsByCategory(CATEGORY_SCOPE);
        }

        SharedPreferenceUtil.getListQuestion(context);
        startActivity(new Intent(context, FirstQuestionActivity.class));


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
}
