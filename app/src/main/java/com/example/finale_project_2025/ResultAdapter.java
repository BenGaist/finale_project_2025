package com.example.finale_project_2025;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private List<Result> resultList;

    public ResultAdapter(List<Result> resultList) {
        this.resultList = resultList;
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, motivationTextView, scoreTextView;

        public ResultViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            motivationTextView = itemView.findViewById(R.id.motivationTextView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
        }
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.usernameTextView.setText("Username: " + result.getUsername());
        holder.motivationTextView.setText(result.getMotivation());
        holder.scoreTextView.setText("Score: " + result.getScore() + "%");
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
