package com.manas.quizapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manas.quizapp.models.CategoryItemModel;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final CategoryItemModel[] listData;

    public ListAdapter(CategoryItemModel[] listData) {
        this.listData = listData;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        final CategoryItemModel myListData = listData[position];
        holder.textView.setText(listData[position].getDescription());
        holder.imageView.setImageResource(listData[position].getImgId());
        holder.relativeLayout.setOnClickListener(view -> {

            Context contextInner = view.getContext();

            Intent intent = new Intent(contextInner, QuizLength.class);
            intent.putExtra("category", myListData.getDescription());
            contextInner.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}