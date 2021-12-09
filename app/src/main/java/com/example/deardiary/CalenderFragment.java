package com.example.deardiary;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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

    ColorModel selectedColor;
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
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        calenderAdapter = new CalenderAdapter(daysInMonth, this, selectedColor);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calenderRecyclerview.setLayoutManager(layoutManager);
        calenderRecyclerview.setAdapter(calenderAdapter);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
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

    @Override
    public  void onItemClick(int position, String dayText, ColorModel colormodel) {
        if(dayText.equals(""))
        {
            Toast.makeText(getContext(), "날짜를 정확히 터치해주세요", Toast.LENGTH_SHORT).show();
        } else {
            showDialog();
        }
    }

    public void showDialog()
    {
        ColorModel colorModel = new ColorModel(false, false, false, false, false, false, false, false);
        CalenderDialog dialog =  new CalenderDialog();
        dialog.setTargetFragment(this, 10);
        dialog.show(getFragmentManager(), "dialog");
        dialog.setcolor(new CalenderDialog.Cml(){
            @Override
            public void finish(ColorModel model){
                calenderAdapter.selectedcolor = model;
                calenderAdapter.notifyDataSetChanged();
            }
        });
    }

}
