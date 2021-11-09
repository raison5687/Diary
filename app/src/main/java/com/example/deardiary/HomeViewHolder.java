package com.example.deardiary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView postTitle;
    public final TextView postDate;
    public final TextView postContent;

    private final com.example.deardiary.HomeAdapter.OnItemListener onItemListener;
    public HomeViewHolder(@NonNull View itemView, com.example.deardiary.HomeAdapter.OnItemListener onItemListener) {
        super(itemView);
        postTitle = itemView.findViewById(R.id.txt_title);
        postDate = itemView.findViewById(R.id.txt_date);
        postContent = itemView.findViewById(R.id.txt_content);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) postTitle.getText());
    }
}
