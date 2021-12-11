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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WritingFragment extends Fragment {
    public FragmentWritingBinding binding;
    Uri image1 = null;
    Uri image2 = null;
    Uri image3 = null;
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
                        image1 = selectedImage;
                    } else if(image2 == null) {
                        image2 = selectedImage;
                    } else {
                        image3 = selectedImage;
                    }
                    Bitmap bitmap = loadBitmap(selectedImage);
                    if(binding.img1.getVisibility() != View.VISIBLE) {
                        binding.img1.setImageBitmap(bitmap);
                        binding.img1.setVisibility(View.VISIBLE);
                    }
                    else if(binding.img2.getVisibility() != View.VISIBLE) {
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

    public void save()
    {
        String title = binding.editTxtWritingTitle.getText().toString();
        String content = binding.editTxtWritingContent.getText().toString();
        String img1 = image1.toString();
        String img2 = image2.toString();
        String img3 = image3.toString();
        Long date = System.currentTimeMillis();
        DiaryModel model = new DiaryModel(title, content, img1, img2, img3, date);
        this.data.add(model);
        try {
//            Map<String, Object> map = new HashMap<>();
//            map.put("title", title);
//            map.put("content", content);
//            map.put("img1", img1);
//            map.put("img2", img2);
//            map.put("img3", img3);
//            map.put("date", date);

            String jsonString = new Gson().toJson(data);
            writeFile(getContext(), "Diaryfile.json", jsonString);

        } catch (Exception e) {
            Toast.makeText(getContext(), "파일을 못찾음", Toast.LENGTH_LONG).show();
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
