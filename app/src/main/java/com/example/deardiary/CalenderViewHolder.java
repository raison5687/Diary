package com.example.deardiary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deardiary.R;

public class CalenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth;
    private final com.example.deardiary.CalenderAdapter.OnItemListener onItemListener;

    public CalenderViewHolder(@NonNull View itemView, com.example.deardiary.CalenderAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.txt_date);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
