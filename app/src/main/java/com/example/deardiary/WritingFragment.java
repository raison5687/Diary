package com.example.deardiary;

import android.Manifest;
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

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deardiary.databinding.FragmentWritingBinding;

import static android.app.Activity.RESULT_OK;

public class WritingFragment extends Fragment {

    public FragmentWritingBinding binding;
    Uri picture;

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
        binding.btnAddPic.setOnClickListener(v -> addPic());
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        return view;
    }

    ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Intent data = result.getData();
                        Log.i("TEST", "data: " + data);

                        if (result.getResultCode() == RESULT_OK && null != data) {
                            Uri selectedImage = data.getData();
                            picture = selectedImage;
                            Bitmap bitmap = loadBitmap(selectedImage);
                            binding.imgInsertPic.setImageBitmap(bitmap);
                        }
                    });

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(getContext(), "권한이 설정되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "권한이 설정되지 않았습니다. 권한이 없으므로 앱을 종료합니다", Toast.LENGTH_SHORT).show();
//                    finish();
                }
            });

    public void addPic() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);
    }

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