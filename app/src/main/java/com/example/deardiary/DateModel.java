package com.example.deardiary;

public class DateModel {

    private String date;
    private ColorModel colorModel;

    public DateModel(String date, ColorModel colorModel) {
        this.date = date;
        this.colorModel = colorModel;
    }

    public String getDate() {return date;}
    public ColorModel getColorModel() {return  colorModel;}
}
