package com.example.deardiary;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deardiary.databinding.FragmentHomeBinding;
import com.example.deardiary.databinding.FragmentHomeBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public FragmentHomeBinding binding;
    ArrayList<DiaryModel> diaryModels;


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

        diaryModels = new ArrayList<DiaryModel>();
        String path = "/data/data/com.example.deardiary/files/Diaryfile.json";
        if(new File(path).exists()) {
            String jsonString = readFile(getContext(), "Diaryfile.json");
            Gson gson = new Gson();
            diaryModels = gson.fromJson(jsonString, new TypeToken<ArrayList<DiaryModel>>(){}.getType());
        }
        HomeAdapter adapter = new HomeAdapter(diaryModels, getContext());
        binding.recyclerViewHome.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewHome.setLayoutManager(manager);

        return view;
    }

    public static String readFile(Context context, String filename) {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return readStream(fis);
    }

    public static String readStream(InputStream inputStream) {
        String contents = "";
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
        } finally {
            contents = stringBuilder.toString().trim();
        }
        return contents;
    }

}
