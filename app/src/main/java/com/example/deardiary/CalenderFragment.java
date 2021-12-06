package com.example.deardiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deardiary.databinding.FragmentCalenderBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class CalenderFragment extends Fragment implements CalenderAdapter.OnItemListener {

    public FragmentCalenderBinding binding;
    AlertDialog.Builder mbuilder;
    private TextView txt_monthYear;
    private RecyclerView calenderRecyclerview;
    private LocalDate selectedDate;

    String selectedColor = new String();
    CalenderAdapter calenderAdapter;

    public CalenderFragment() { }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        binding = FragmentCalenderBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
        binding.btnPre.setOnClickListener(v -> previousMonth());
        binding.btnNext.setOnClickListener(v -> NextMonth());
        mbuilder = new AlertDialog.Builder(getContext());
        return view;
    }

    private static class CalenderFragmentHolder {
        public static final CalenderFragment INSTANCE = new CalenderFragment();
    }

    public static CalenderFragment newInstance()
    {
        return CalenderFragment.CalenderFragmentHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void initWidgets()
    {
        calenderRecyclerview = binding.recyclerCalender;
        txt_monthYear = binding.txtCalender;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {
        txt_monthYear.setText(monthYearFromData(selectedDate));
        ArrayList<CalenderAdapter.Item> daysInMonth = daysInMonthArray(selectedDate);
        if(selectedColor.equals("")) {
            calenderAdapter = new CalenderAdapter(daysInMonth, this, selectedColor);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
            calenderRecyclerview.setLayoutManager(layoutManager);
            calenderRecyclerview.setAdapter(calenderAdapter);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<CalenderAdapter.Item> daysInMonthArray(LocalDate date)
    {
        ArrayList<CalenderAdapter.Item> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
//                daysInMonthArray.add();
            }
            else
            {
//                daysInMonthArray.add(new CalenderAdapter.Item(String.valueOf(i - dayOfWeek)));
            }
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromData(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy").withLocale(Locale.KOREA);
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonth()
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void NextMonth()
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText, String selectedColor)
    {
        if(dayText.equals(""))
        {
            Toast.makeText(getContext(), "날짜를 정확히 터치해주세요", Toast.LENGTH_SHORT).show();
        } else {
            showDialog();
            calenderAdapter.notifyItemChanged(position, selectedColor);

//            Intent intent = getActivity().getIntent();
//            boolean[] selects = intent.getBooleanArrayExtra("selects");
        }
    }

    public void showDialog()
    {
        DialogFragment dialog =  new CalenderDialog();
        dialog.setTargetFragment(this, 10);
        dialog.show(getFragmentManager(), "dialog");
    }



}
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode,data);
//        if(requestCode == 10)
//        {
//            if(requestCode == Activity.RESULT_OK)
//            {
//
//            }
//        }
//    }