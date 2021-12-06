package com.example.deardiary;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder> {

    public final ArrayList<Item> dateOfMonth;
    public final OnItemListener onItemListener;
    String selectedColor;


    public CalenderAdapter(ArrayList<Item> dateOfMonth, OnItemListener onItemListener, String red) {
        this.selectedColor = selectedColor;
        this.dateOfMonth = dateOfMonth;
        this.onItemListener = onItemListener;
    }
    public static class Item{
        String dateOfMonth;
        boolean isRed;
        public Item(String dateOfMonth, boolean isRed) {
            this.dateOfMonth = dateOfMonth;
            this.isRed = isRed;
        }
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_calender, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int)(parent.getHeight() * 0.166666666);
        return new CalenderViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position) {
        Item item = dateOfMonth.get(position);
        holder.dayOfMonth.setText(item.dateOfMonth);
        if (item.isRed) {
            holder.red.setVisibility(View.VISIBLE);
        }else
            holder.red.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return dateOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText, String selectedColor);
    }
}
