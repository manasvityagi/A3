package com.manas.quizapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.manas.quizapp.models.ScoreRecordModel;
import org.jetbrains.annotations.NotNull;
import java.util.List;


public class PastRecordAdapter extends RecyclerView.Adapter<PastRecordAdapter.MyViewHolder> {

    List<ScoreRecordModel>  recordList;

    public PastRecordAdapter(List<ScoreRecordModel> scoreList) {
        this.recordList = scoreList;
    }


    @NotNull
    @Override
    public PastRecordAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.past_record_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PastRecordAdapter.MyViewHolder holder, int position) {

        holder.usernameTv.setText(recordList.get(position).getUsername());
        holder.sessionTimestampTv.setText(recordList.get(position).getSessionTS());
        holder.categoryTv.setText(recordList.get(position).getCategory());
        holder.scoreTv.setText(recordList.get(position).getScore());
        holder.quizLengthTv.setText(recordList.get(position).getQuizLength());

    }

    @Override
    public int getItemCount() {
        return this.recordList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTv;
        TextView sessionTimestampTv;
        TextView categoryTv;
        TextView scoreTv;
        TextView quizLengthTv;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.usernameTv = itemView.findViewById(R.id.userTextView);
            this.sessionTimestampTv = itemView.findViewById(R.id.timeStampTextView);
            this.categoryTv = itemView.findViewById(R.id.categoryTextView);
            this.scoreTv = itemView.findViewById(R.id.scoreTextView);
            this.quizLengthTv = itemView.findViewById(R.id.quizLengthTextView);
        }
    }
}
