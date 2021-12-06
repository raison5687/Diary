package com.example.deardiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    public final ArrayList<String> postTitle;
    public final HomeAdapter.OnItemListener onItemListener;

    public HomeAdapter(ArrayList<String> postTitle, HomeAdapter.OnItemListener onItemListener) {
        this.postTitle = postTitle;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_home, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int)(parent.getHeight() * 0.166666666);
        return new HomeViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.postTitle.setText(postTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return postTitle.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }

}
