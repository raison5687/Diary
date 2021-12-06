package com.example.deardiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deardiary.databinding.FragmentWritingBinding;

import java.util.ArrayList;

public class WritingFragment extends Fragment {
    public FragmentWritingBinding binding;

    public WritingFragment(){}

    private static class SettingFragmentHolder {
        public static final WritingFragment INSTANCE = new WritingFragment();
    }

    public static WritingFragment newInstance() {
        return SettingFragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWritingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.btnWritingPlus.setOnClickListener(v -> add());

        return view;
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->
            {
                if (isGranted) {
                    Toast.makeText(getContext(), "권한이 설정되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "권한이 설정되지 않았습니다, 권한이 없으므로 앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            });

    ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                Intent data = result.getData();
                if (result.getResultCode() == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    Bitmap bitmap = loadBitmap(selectedImage);
                    binding.img1.setImageResource(bitmap);
                    binding.img1.setVisibility(View.VISIBLE);
                    
                }


            });

    private void add(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);
    }

}