package com.example.deardiary;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deardiary.databinding.ItemHomeBinding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    ArrayList<DiaryModel> diaryModels;
    Context context;
    public HomeAdapter(ArrayList<DiaryModel> data, Context context)
    {
        diaryModels = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemHomeBinding binding = ItemHomeBinding.inflate(inflater, parent, false);
//        View view = inflater.inflate(R.layout.item_home, parent, false);
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.height = (int)(parent.getHeight() * 0.166666666);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtHomeTitle.setText(diaryModels.get(position).getTitle());
        holder.binding.txtHomeDate.setText(diaryModels.get(position).getDate().toString());
        holder.binding.txtHomeContent.setText(diaryModels.get(position).getContent());

        String image1 = diaryModels.get(position).getImg1();
        String image2 = diaryModels.get(position).getImg2();
        String image3 = diaryModels.get(position).getImg3();

        Uri u1 = Uri.parse(image1);
        Uri u2 = Uri.parse(image2);
        Uri u3 = Uri.parse(image3);

//        Log.i("ADAPTERURI", u1.toString());
//        holder.binding.imgHome2.setImageURI(u2);
//        holder.binding.imgHome3.setImageURI(u3);
        Glide.with(context)
                .load(u1)
                .into(holder.binding.imgHome1);

        Glide.with(context)
                .load(u2)
                .into(holder.binding.imgHome2);

        Glide.with(context)
                .load(u3)
                .into(holder.binding.imgHome3);

    }

    @Override
    public int getItemCount() {
        return diaryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeBinding binding;

        public ViewHolder(ItemHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
