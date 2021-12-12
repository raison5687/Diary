package com.example.deardiary;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.security.Timestamp;
import java.text.SimpleDateFormat;
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
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtHomeTitle.setText(diaryModels.get(position).getTitle());
        holder.binding.txtHomeDate.setText(diaryModels.get(position).getDate().toString());
        holder.binding.txtHomeContent.setText(diaryModels.get(position).getContent());
<<<<<<< HEAD
//        Glide.with(holder.binding.imgHome1)
//                .asBitmap()
//                .load(diaryModels.get(position).getImg1())
//                .into(holder.binding.imgHome1);
//        Glide.with(holder.binding.imgHome2).asBitmap()
//                .load(diaryModels.get(position).getImg1())
//                .into(holder.binding.imgHome2);
//        Glide.with(holder.binding.imgHome3).asBitmap()
//                .load(diaryModels.get(position).getImg1())
//                .into(holder.binding.imgHome3);
=======
        long mills = diaryModels.get(position).getDate();

        String date = MillToDate(mills);
        holder.binding.txtHomeDate.setText(date);
>>>>>>> b858e60eb7f573806a460fddbb57b2bff90ad25e

        String image1 = diaryModels.get(position).getImg1();
        String image2 = diaryModels.get(position).getImg2();
        String image3 = diaryModels.get(position).getImg3();

        Log.i("ADAPTERURI", image1);
        Glide.with(context)
                .load(image1)
                .into(holder.binding.imgHome1);

        Glide.with(context)
                .load(image2)
                .into(holder.binding.imgHome2);

        Glide.with(context)
                .load(image3)
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
    public String MillToDate(long mills) {
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String date = formatter.format(mills);
        return date;
    }
}
