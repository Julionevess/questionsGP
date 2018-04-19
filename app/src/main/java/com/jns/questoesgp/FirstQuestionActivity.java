package com.jns.questoesgp;

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

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstQuestionActivity extends AppCompatActivity {


    private static final String CORRECT = "Certo";
    private static final String WRONG = "Errado";

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


    public static int totalQuestionSelected = 10;
    public static List<Answer> answers;
    public static List<Question> questionsSelected;
    public static List<Question> questionsAll;
    public static List<Question> questionsByCategory;
    public static String Category;

    /**
     * @param args
     */
    public static void main(String[] args) {

        catchAllQuestions();
        catchSelectedQuestions();


    }
    /*
     * Catch all json questions
     */
    public static void catchAllQuestions() {
        questionsAll = new ArrayList<Question>();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader("C:\\temp\\questions.json"));
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
        questionsSelected = new ArrayList<Question>();
        Random r = new Random();
        for (int i = 0 ; i < totalQuestionSelected ; i++){

            Question categorizedQuestion = questionsAll.get(r.nextInt(totalQuestionSelected));
            if (categorizedQuestion.getCategory().equals(category)){
                questionsSelected.add(categorizedQuestion);
            }
        }
    }

    /*
     * Correct Questions
     */
    public static void correctQuestions(){

        for (int i = 0; i < questionsSelected.size() ; i++) {
            if (questionsSelected.get(i).getAnswer().equals(answers.get(i).getAnswer())){
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

        TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        RadioButton rbOptionOne = (RadioButton) findViewById((R.id.rbOptionOne));
        RadioButton rbOptionTwo = (RadioButton) findViewById((R.id.rbOptionTwo));
        RadioButton rbOptionThree = (RadioButton) findViewById((R.id.rbOptionThree));
        RadioButton rbOptionFour = (RadioButton) findViewById((R.id.rbOptionFour));
        RadioButton rbOptionFive = (RadioButton) findViewById((R.id.rbOptionFive));

        tvQuestion.setText("Qual o seu nome?");
        rbOptionOne.setText("");
        rbOptionTwo.setText("");
        rbOptionThree.setText("");
        rbOptionFour.setText("");
        rbOptionFive.setText("");

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
}
