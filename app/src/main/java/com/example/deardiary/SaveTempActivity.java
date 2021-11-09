package com.example.deardiary;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deardiary.databinding.LayoutSavetempBinding;

public class SaveTempActivity extends AppCompatActivity {
    private LayoutSavetempBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutSavetempBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
