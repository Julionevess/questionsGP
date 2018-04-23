package com.jns.questoesgp.adapter.holders;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.jns.questoesgp.AnswerActivity;
import com.jns.questoesgp.adapter.AnswerAdapter;
import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.questoesgp.R;


public class AnswerViewHolders extends RecyclerView.ViewHolder {

    public ImageView customerImage;
    public TextView answer;
    public TextView correctAnswer;
    public TextView question;

    public AnswerViewHolders(final Activity activity, View itemView, final AnswerAdapter adapter) {
        super(itemView);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer answer = adapter.getItems().get(getAdapterPosition());
                Intent intent = new Intent(activity, AnswerActivity.class);
                if (answer != null) {
                    Gson gson = new Gson();
                    //intent.putExtra(Constants.CUSTOMER, gson.toJson(customer));
                    //intent.putExtra(Constants.STOREKEEPER, gson.toJson(adapter.getStorekeeper()));

                    activity.startActivity(intent);
                }
            }
		});

        customerImage = (ImageView) itemView.findViewById(R.id.iv_customer);
        answer = (TextView) itemView.findViewById(R.id.tv_answer);
        correctAnswer = (TextView) itemView.findViewById(R.id.tv_correct_answer);
        question = (TextView) itemView.findViewById(R.id.tv_question);

    }
}
