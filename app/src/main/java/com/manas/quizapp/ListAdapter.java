package com.manas.quizapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manas.quizapp.ChoseCategory;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private CategoryItemModel[] listdata;
    private final Context context = null;
    // RecyclerView recyclerView;
    public ListAdapter(CategoryItemModel[] listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        final CategoryItemModel myListData = listdata[position];
        holder.textView.setText(listdata[position].getDescription());
        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "clicked on item: " + myListData.getDescription(), Toast.LENGTH_LONG).show();
                Context context = view.getContext();

                Intent intent = new Intent(context, QuizLength.class);
                intent.putExtra("category", myListData.getDescription());
                context.startActivity(intent);
            }
        });
    }

//    @Override
//    public void onBindViewHolder(@NonNull @NotNull ListAdapter.ViewHolder holder, int position) {
//        final CategoryItemModel myListData = listdata[position];
//        holder.textView.setText(listdata[position].getDescription());
//        holder.imageView.setImageResource(listdata[position].getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "clicked on item: " + myListData.getDescription(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        final CategoryItemModel myListData = listdata[position];
//        holder.textView.setText(listdata[position].getDescription());
//        holder.imageView.setImageResource(listdata[position].getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "clicked on item: " + myListData.getDescription(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}