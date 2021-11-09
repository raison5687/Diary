package com.example.deardiary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deardiary.databinding.LayoutWritingBinding;

public class WriteActivity extends AppCompatActivity {
    private LayoutWritingBinding binding;
    Uri picture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutWritingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAddPic.setOnClickListener(v -> addPic());
//        binding.btnSave.setOnClickListener(v -> save());
        binding.btnTempSave.setOnClickListener(v -> saveTemp());


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
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
                    Toast.makeText(this, "권한이 설정되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "권한이 설정되지 않았습니다. 권한이 없으므로 앱을 종료합니다", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

    public void addPic() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);
    }

    private Bitmap loadBitmap(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        return BitmapFactory.decodeFile(picturePath);
    }

//    public void save() {
//
//        Intent intent = new Intent(this, HomeFragment.class);
//
//        String content = binding.editTxtTitle.getText().toString();
//        intent.putExtra("content", content);
//
//        String title = binding.editTxtWriting.getText().toString();
//        intent.putExtra("title", title);
//
//        intent.putExtra("date", System.currentTimeMillis());
//
//        intent.putExtra("picture", picture);
//
//        startActivity(intent);
//
//    }

    public void saveTemp() {
        Intent intent = new Intent(this, SaveTempActivity.class);

//        String content = binding.editTxtWriting.getText().toString();
//        intent.putExtra("content", content);
//
//        String name = binding.editTextName.getText().toString();
//        intent.putExtra("name", name);
//
//        intent.putExtra("picture", picture);

        startActivity(intent);
    }

//    public void
}