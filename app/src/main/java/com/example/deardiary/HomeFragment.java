package com.example.deardiary;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deardiary.databinding.FragmentHomeBinding;
import com.example.deardiary.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    public FragmentHomeBinding binding;

    public HomeFragment() { }

    private static class HomeFragmentHolder {
        public static final HomeFragment INSTANCE = new HomeFragment();
    }

    public static HomeFragment newInstance() {
        return HomeFragment.HomeFragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.btnWrite.setOnClickListener(v -> write());
        return view;
    }


    public void write()
    {
        Intent intent = new Intent(getActivity(), WriteActivity.class);
        startActivity(intent);
    }
}
