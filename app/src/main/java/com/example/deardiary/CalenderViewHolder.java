package com.example.deardiary;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deardiary.R;

public class CalenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth, red, yellow, green, blue, darkblue, gray, pink, bisque;
    private final com.example.deardiary.CalenderAdapter.OnItemListener onItemListener;

    public CalenderViewHolder(@NonNull View itemView, com.example.deardiary.CalenderAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.txt_date);
        red = itemView.findViewById(R.id.red);
        yellow = itemView.findViewById(R.id.yellow);
        green = itemView.findViewById(R.id.green);
        blue = itemView.findViewById(R.id.blue);
        darkblue = itemView.findViewById(R.id.dark_blue);
        gray = itemView.findViewById(R.id.gray);
        pink = itemView.findViewById(R.id.pink);
        bisque = itemView.findViewById(R.id.bisque);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String selectedColor = "";
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText(), selectedColor);
        if(selectedColor.equals("red")) {
            red.setVisibility(View.VISIBLE);
        }
    }
}
