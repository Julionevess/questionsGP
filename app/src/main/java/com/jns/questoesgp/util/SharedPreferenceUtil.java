package com.jns.questoesgp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jns.questoesgp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceUtil {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String KIND_OF_LOGIN = "kind_of_login";
    public static final String LIST_QUESTIONS = "listQuestion";

    public static List<Question> getListQuestion(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(LIST_QUESTIONS, null);
        return new ArrayList<>();
    }

    public static String getQuestion(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return prefs.getString(KIND_OF_LOGIN, null);
    }

    public static void setListQuestion(Context context, String type) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();

        editor.putString(KIND_OF_LOGIN, type);
        editor.commit();
    }


}
