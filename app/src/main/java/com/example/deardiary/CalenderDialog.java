package com.example.deardiary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

public class CalenderDialog extends DialogFragment
{
    AlertDialog dialog;
    TextView btn_red, btn_yellow, btn_blue, btn_bisque, btn_gray, btn_pink, btn_green, btn_dark_blue;
    AppCompatButton btn_confirm;
    Cml mycolor;
//    public CalenderDialog(ColorModel colorModel) {
//        this.colorModel = colorModel
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_palette, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btn_red = view.findViewById(R.id.btn_red);
        btn_bisque = view.findViewById(R.id.btn_bisque);
        btn_blue = view.findViewById(R.id.btn_blue);
        btn_dark_blue = view.findViewById(R.id.btn_darkblue);
        btn_gray = view.findViewById(R.id.btn_gray);
        btn_green = view.findViewById(R.id.btn_green);
        btn_pink = view.findViewById(R.id.btn_pink);
        btn_yellow = view.findViewById(R.id.btn_yellow);
        btn_confirm = view.findViewById(R.id.btn_confirm);

        btn_red.setOnClickListener(selected);
        btn_yellow.setOnClickListener(selected);
        btn_blue.setOnClickListener(selected);
        btn_dark_blue.setOnClickListener(selected);
        btn_gray.setOnClickListener(selected);
        btn_green.setOnClickListener(selected);
        btn_pink.setOnClickListener(selected);
        btn_bisque.setOnClickListener(selected);
        btn_confirm.setOnClickListener(confirm);

        return dialog;
    }

    View.OnClickListener selected = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btn_red :
                    if(btn_red.isSelected()){
                        btn_red.setSelected(false);
                    } else {
                        btn_red.setSelected(true);
                    }
                    break;
                case R.id.btn_bisque :
                    if(btn_bisque.isSelected()){
                        btn_bisque.setSelected(false);
                    } else {
                        btn_bisque.setSelected(true);
                    }
                    break;
                case R.id.btn_blue :
                    if(btn_blue.isSelected()){
                        btn_blue.setSelected(false);
                    } else {
                        btn_blue.setSelected(true);
                    }
                    break;
                case R.id.btn_gray :
                    if(btn_gray.isSelected()){
                        btn_gray.setSelected(false);
                    } else {
                        btn_gray.setSelected(true);
                    }
                    break;
                case R.id.btn_green :
                    if(btn_green.isSelected()){
                        btn_green.setSelected(false);
                    } else {
                        btn_green.setSelected(true);
                    }
                    break;
                case R.id.btn_pink :
                    if(btn_pink.isSelected()){
                        btn_pink.setSelected(false);
                    } else {
                        btn_pink.setSelected(true);
                    }
                    break;
                case R.id.btn_yellow :
                    if(btn_yellow.isSelected()){
                        btn_yellow.setSelected(false);
                    } else {
                        btn_yellow.setSelected(true);
                    }
                    break;
                case R.id.btn_darkblue :
                    if(btn_dark_blue.isSelected()){
                        btn_dark_blue.setSelected(false);
                    } else {
                        btn_dark_blue.setSelected(true);
                    }
                    break;
            }
        }
    };
    View.OnClickListener confirm = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Boolean red = btn_red.isSelected();
            Boolean bisque = btn_bisque.isSelected();
            Boolean blue = btn_blue.isSelected();
            Boolean gray = btn_gray.isSelected();
            Boolean green = btn_green.isSelected();
            Boolean pink = btn_pink.isSelected();
            Boolean yellow = btn_yellow.isSelected();
            Boolean darkblue = btn_dark_blue.isSelected();
            ColorModel model = new ColorModel(red, bisque, blue, gray,
                    green, pink, yellow, darkblue);
            mycolor.finish(model);
            dialog.dismiss();
        }
    };
    public void setcolor(Cml colorModel) {
        mycolor = colorModel;
    }
    public interface Cml {
        void finish(ColorModel model);
    }
}
