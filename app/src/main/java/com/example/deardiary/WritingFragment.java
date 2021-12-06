package com.example.deardiary;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
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
    private void add(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                Intent data = result.getData();
                if (result.getResultCode() == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    Bitmap bitmap = loadBitmap(selectedImage);
                    if(binding.img1.getVisibility() != View.VISIBLE) {
                        binding.img1.setImageBitmap(bitmap);
                        binding.img1.setVisibility(View.VISIBLE);
                    } else if(binding.img2.getVisibility() != View.VISIBLE) {
                        binding.img2.setImageBitmap(bitmap);
                        binding.img2.setVisibility(View.VISIBLE);
                    } else {
                        binding.img3.setImageBitmap(bitmap);
                        binding.img3.setVisibility(View.VISIBLE);
                    }
                }
            });

    private Bitmap loadBitmap(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        return BitmapFactory.decodeFile(picturePath);
    }


}