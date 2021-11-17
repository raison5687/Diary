package com.example.deardiary;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deardiary.databinding.FragmentHomeBinding;
import com.example.deardiary.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    public FragmentHomeBinding binding;

    public HomeFragment() { }

    private static class HomeFragmentHolder {
        public static final HomeFragment INSTANCE = new HomeFragment();
    }

    public static HomeFragment newInstance() {
        return HomeFragment.HomeFragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
//
//        String title = this.getArguments().getString("title");
//        String content = this.getArguments().getString("content");



//        Intent intent = getIntent();
//
//        String contentSaved = intent.getStringExtra("content");
//        binding.writeSaved.setText("" + contentSaved);
//
//        String nameSaved = intent.getStringExtra("name");
//        binding.nameSaved.setText("" + nameSaved);
//
//        Uri pictureSaved = intent.getParcelableExtra("picture");
//        Bitmap picture = loadBitmap(pictureSaved);
//        binding.pictureSaved.setImageBitmap(picture);
//        Log.i("picture", "Pic");
    }


//
//    private Bitmap loadBitmap(Uri uri) {
//        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//        Cursor cursor = getContentResolver().query(uri,
//                filePathColumn, null, null, null);
//        cursor.moveToFirst();
//
//        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//        String picturePath = cursor.getString(columnIndex);
//        cursor.close();
//
//        return BitmapFactory.decodeFile(picturePath);
//    }
}
