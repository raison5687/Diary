package com.example.deardiary;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deardiary.R;
import com.example.deardiary.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {

    public FragmentSettingBinding binding;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
}