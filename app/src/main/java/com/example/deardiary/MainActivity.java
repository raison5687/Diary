package com.example.deardiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private SettingFragment settingFragment;
    private CalenderFragment calenderFragment;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = HomeFragment.newInstance();
        settingFragment = SettingFragment.newInstance();
        calenderFragment = CalenderFragment.newInstance();

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
                        case R.id.nav_setting:
                            transaction.replace(R.id.fragment_container, settingFragment).commitAllowingStateLoss();
                            break;
                    }
                    return true;
                }
            };

}