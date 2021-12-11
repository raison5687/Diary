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

    public final ArrayList<DateModel> dateOfMonth;
    public final OnItemListener onItemListener;
    public CalenderViewHolder myholder;

    public CalenderAdapter(ArrayList<DateModel> dateOfMonth, OnItemListener onItemListener) {
        this.dateOfMonth = dateOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_calender, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int)(parent.getHeight() * 0.166666666);
        myholder = new CalenderViewHolder(view, onItemListener);
        return new CalenderViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position) {
        DateModel value = dateOfMonth.get(position);
        holder.dayOfMonth.setText(value.getDate());
        if(value.getColorModel() != null) {
            if(value.getColorModel().isRed()) {
                holder.red.setVisibility(View.VISIBLE);
            }
            if(value.getColorModel().isBisque()) {
                holder.bisque.setVisibility(View.VISIBLE);
            }
            if(value.getColorModel().isBlue()) {
                holder.blue.setVisibility(View.VISIBLE);
            }
            if(value.getColorModel().isGray()) {
                holder.gray.setVisibility(View.VISIBLE);
            }
            if(value.getColorModel().isGreen()) {
                holder.green.setVisibility(View.VISIBLE);
            }
            if(value.getColorModel().isPink()) {
                holder.pink.setVisibility(View.VISIBLE);
            }
            if(value.getColorModel().isYellow()) {
                holder.yellow.setVisibility(View.VISIBLE);
            }
            if(value.getColorModel().isDarkblue()) {
                holder.darkblue.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dateOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText, ColorModel colormodel);
    }
}