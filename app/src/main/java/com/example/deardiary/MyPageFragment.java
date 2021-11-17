package com.example.deardiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.deardiary.databinding.FragmentMypageBinding;

public class MyPageFragment extends Fragment {
    public FragmentMypageBinding binding;

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
        return view;
    }
}