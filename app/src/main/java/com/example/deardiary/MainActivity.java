package com.example.deardiary;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private WritingFragment writingFragment;
    private CalenderFragment calenderFragment;
    private FragmentTransaction transaction;
    private MyPageFragment myPageFragment;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = HomeFragment.newInstance();
        writingFragment = WritingFragment.newInstance();
        calenderFragment = CalenderFragment.newInstance();
        myPageFragment = MyPageFragment.newInstance();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, homeFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.getMenu().getItem(1).setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(navListener);



    }


    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    transaction = fragmentManager.beginTransaction();

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            transaction.replace(R.id.fragment_container, homeFragment).commitAllowingStateLoss();
                            break;
                        case R.id.nav_calender:
                            transaction.replace(R.id.fragment_container, calenderFragment).commitAllowingStateLoss();
                            break;
                        case R.id.nav_writing:
                            transaction.replace(R.id.fragment_container, writingFragment).commitAllowingStateLoss();
                            break;
                        case R.id.nav_mypage:
                            transaction.replace(R.id.fragment_container, myPageFragment).commitAllowingStateLoss();
                            break;
                    }
                    return true;
                }
            };


}