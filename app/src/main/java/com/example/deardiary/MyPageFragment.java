package com.example.deardiary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.deardiary.databinding.FragmentMypageBinding;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class MyPageFragment extends Fragment {
    public FragmentMypageBinding binding;
    final static int TAKE_PICTURE = 1;
    String mCurrentPhotoPath;
    final static int REQUEST_TAKE_PHOTO = 1;


    public MyPageFragment() { }

    private static class MyPageFragmentHolder {
        public static final MyPageFragment INSTANCE = new MyPageFragment();
    }

    public static MyPageFragment newInstance() {
        return MyPageFragment.MyPageFragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "?????? ?????? ??????");
                } else {
                    Log.d(TAG, "?????? ?????? ??????");
                    ActivityCompat.requestPermissions(getActivity(), new String[]
                            {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
        }
        binding.txtName.setEnabled(false);
        binding.btnImage.setOnClickListener( v -> getImage());
        binding.btnName.setOnClickListener( v -> nameClick());
        binding.btnSave.setOnClickListener(v -> save());
        return view;
    }


    public void nameClick(){
        binding.txtName.setEnabled(true);
        binding.txtName.setText(null);
        binding.btnName.setVisibility(View.GONE);
        binding.btnSave.setVisibility(View.VISIBLE);
    }
    public void save(){
        binding.txtName.setEnabled(false);
        binding.btnSave.setVisibility(View.GONE);
        binding.btnName.setVisibility(View.VISIBLE);
    }

    public void getImage(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);


        switch (R.id.btn_image) {
            case R.id.btn_image:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PICTURE);
                break;
        }
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->{
                if(isGranted){
                    Toast.makeText(getContext(), "????????? ?????????????????????", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "????????? ???????????? ??????????????? ????????? ???????????? ?????? ???????????????", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            });

    ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Intent data = result.getData();
                        Log.i("TEST", "data: " + data);

                        if (result.getResultCode() == RESULT_OK && null != data) {
                            Uri selectedImage = data.getData();
                            Bitmap bitmap = loadBitmap(selectedImage);
                            Glide.with(this)
                                    .load(selectedImage)
                                    .circleCrop()
                                    .into(binding.imageView);
                            binding.imageView.setImageBitmap(bitmap);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK && intent.hasExtra("data")) {
                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                    if (bitmap != null) {
                        binding.imageView.setImageBitmap(bitmap);
                    }
                }
                break;
        }
    }


}