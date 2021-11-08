package com.example.deardiary;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deardiary.databinding.LayoutWritingBinding;

public class WriteActivity extends AppCompatActivity {
    private LayoutWritingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutWritingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}