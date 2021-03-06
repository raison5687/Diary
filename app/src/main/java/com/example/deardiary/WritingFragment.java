package com.example.deardiary;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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

import com.bumptech.glide.Glide;
import com.example.deardiary.databinding.FragmentWritingBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WritingFragment extends Fragment {
    public FragmentWritingBinding binding;
    String image1 = null;
    String image2 = null;
    String image3 = null;
    private ArrayList<DiaryModel> data = new ArrayList<>();

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
        binding.btnWritingSave.setOnClickListener(v -> save());
        long time = System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String date = dateFormat.format(time);
        binding.txtWritingDate.setText(date);
        String path = "/data/data/com.example.deardiary/files/Diaryfile.json";
        if(new File(path).exists()) {
            String jsonString = readFile(getContext(), "Diaryfile.json");
            data = new Gson().fromJson(jsonString, new TypeToken<ArrayList<DiaryModel>>() {
            }.getType());
        }
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
                    if(image1 == null) {
                        image1 = uriToPath(selectedImage);
                    } else if(image2 == null) {
                        image2 = uriToPath(selectedImage);
                    } else {
                        image3 = uriToPath(selectedImage);
                    }
                    if(binding.img1.getVisibility() != View.VISIBLE) {
                        Glide.with(getContext())
                                .load(image1)
                                .into(binding.img1);
                        binding.img1.setVisibility(View.VISIBLE);
                    }
                    else if(binding.img2.getVisibility() != View.VISIBLE) {
                        Glide.with(getContext())
                                .load(image2)
                                .into(binding.img2);
                        binding.img2.setVisibility(View.VISIBLE);
                    } else {
                        Glide.with(getContext())
                                .load(image3)
                                .into(binding.img3);
                        binding.img3.setVisibility(View.VISIBLE);
                    }
                }
            });

    private String uriToPath(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        return picturePath;
    }

    public void save()
    {
        String title = binding.editTxtWritingTitle.getText().toString();
        String content = binding.editTxtWritingContent.getText().toString();
        String img1 = image1.toString();
        Uri imgUri1 = Uri.parse(img1);
        Log.i("TEST", "" + imgUri1);

        String img2 = image2.toString();
        String img3 = image3.toString();
        Long date = System.currentTimeMillis();
        DiaryModel model = new DiaryModel(title, content, img1, img2, img3, date);
        this.data.add(model);
        try {
            String jsonString = new Gson().toJson(data);
            writeFile(getContext(), "Diaryfile.json", jsonString);
        } catch (Exception e) {
            Toast.makeText(getContext(), "????????? ?????????", Toast.LENGTH_LONG).show();
        }

    }

    public static void writeFile(Context context, String filename, String data) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
