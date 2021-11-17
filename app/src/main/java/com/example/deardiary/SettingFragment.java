package com.example.deardiary;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.deardiary.R;
import com.example.deardiary.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {

    public SettingFragment(){}

    private static class SettingFragmentHolder {
        public static final SettingFragment INSTANCE = new SettingFragment();
    }

    public static SettingFragment newInstance() {
        return SettingFragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);

        edit =(ImageView)view.findViewById(R.id.btn_edit);
        profile = (ImageView)view.findViewById(R.id.btn_profile);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
        return view;
    }



}