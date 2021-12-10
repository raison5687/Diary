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

    public final ArrayList<String> dateOfMonth;
    public final OnItemListener onItemListener;
    public ColorModel selectedcolor;
    public CalenderViewHolder myholder;


    public CalenderAdapter(ArrayList<String> dateOfMonth, OnItemListener onItemListener, ColorModel selectedColor) {
        this.dateOfMonth = dateOfMonth;
        this.selectedcolor = selectedColor;
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
        holder.dayOfMonth.setText(dateOfMonth.get(position));
//        if(selectedcolor.isRed()){
//            holder.red.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return dateOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText, ColorModel colormodel);
    }
}