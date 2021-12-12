package com.example.deardiary;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    ArrayList<DiaryModel> diaryModels;

    public HomeAdapter(ArrayList<DiaryModel> data) {
        diaryModels = data;
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



        Glide.with(holder.binding.imgHome1)
                .asBitmap()
                .load(diaryModels.get(position).getImg1())
                .into(holder.binding.imgHome1);
//        Log.i("TEST", "" + );
        Glide.with(holder.binding.imgHome2).asBitmap()
                .load(diaryModels.get(position).getImg1())
                .into(holder.binding.imgHome2);
        Glide.with(holder.binding.imgHome3).asBitmap()
                .load(diaryModels.get(position).getImg1())
                .into(holder.binding.imgHome3);

//        Uri img1 = intent.getParcelableExtra("picture");
//        Bitmap picture = loadBitmap(pictureSaved);
//        binding.pictureSaved.setImageBitmap(picture);
//        Log.i("picture", "Pic");
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
