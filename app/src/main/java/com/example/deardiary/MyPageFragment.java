package com.example.deardiary;

import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/com/example/deardiary/SettingFragment.java
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

=======
>>>>>>> 7c1c45749cc417325e717dde178a85546484d42b:app/src/main/java/com/example/deardiary/MyPageFragment.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.deardiary.databinding.FragmentMypageBinding;

<<<<<<< HEAD:app/src/main/java/com/example/deardiary/SettingFragment.java
    public SettingFragment(){}
=======
public class MyPageFragment extends Fragment {
    public FragmentMypageBinding binding;

    public MyPageFragment() { }
>>>>>>> 7c1c45749cc417325e717dde178a85546484d42b:app/src/main/java/com/example/deardiary/MyPageFragment.java

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
<<<<<<< HEAD:app/src/main/java/com/example/deardiary/SettingFragment.java
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
=======
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
}
>>>>>>> 7c1c45749cc417325e717dde178a85546484d42b:app/src/main/java/com/example/deardiary/MyPageFragment.java
