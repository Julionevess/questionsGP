package com.jns.questoesgp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jns.questoesgp.adapter.holders.AnswerViewHolders;
import com.jns.questoesgp.model.Answer;
import com.jns.questoesgp.questoesgp.R;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolders> {

    private Activity activity;
    private List<Answer> items;

    public AnswerAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public AnswerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                answer_item, null);
        return new AnswerViewHolders(activity, layoutView, this);
    }

    @Override
    public void onBindViewHolder(final AnswerViewHolders holder, final int position) {

        final Answer holderItem = items.get(position);

        if (holderItem.getAnswer() != null && !holderItem.getAnswer().trim().equals("")) {
            holder.answer.setVisibility(View.VISIBLE);
            holder.answer.setText(holderItem.getAnswer());
        } else {
            holder.answer.setVisibility(View.GONE);
        }
        if (holderItem.getCorrectAnswer() != null && !holderItem.getCorrectAnswer().trim().equals("")) {
            holder.correctAnswer.setVisibility(View.VISIBLE);
            holder.correctAnswer.setText(holderItem.getCorrectAnswer());
        } else {
            holder.answer.setVisibility(View.GONE);
        }
        if (holderItem.getQuestion() != null && !holderItem.getQuestion().trim().equals("")) {
            holder.question.setVisibility(View.VISIBLE);
            holder.question.setText(holderItem.getQuestion());
        } else {
            holder.question.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Answer> getItems() {
        return items;
    }

    public void setItems(List<Answer> items) {
        this.items = items;
    }


}
