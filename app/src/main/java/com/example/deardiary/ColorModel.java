package com.example.deardiary;

public class ColorModel {
    private boolean red, bisque, blue, gray, green, pink, yellow, darkblue;

    public ColorModel(boolean red, boolean bisque, boolean blue, boolean gray, boolean green, boolean pink, boolean yellow, boolean darkblue) {
        this.red = red;
        this.bisque = bisque;
        this.blue = blue;
        this.gray = gray;
        this.green = green;
        this.pink = pink;
        this.yellow = yellow;
        this.darkblue = darkblue;
    }

    public boolean isRed() { return red; }
    public void setRed(boolean red) {
        this.red = red;
    }
    public boolean isBisque() {
        return bisque;
    }
    public void setBisque(boolean bisque) {
        this.bisque = bisque;
    }
    public boolean isBlue() {
        return blue;
    }
    public void setBlue(boolean blue) {
        this.blue = blue;
    }
    public boolean isGray() {
        return gray;
    }
    public void setGray(boolean gray) {
        this.gray = gray;
    }
    public boolean isGreen() {
        return green;
    }
    public void setGreen(boolean green) {
        this.green = green;
    }
    public boolean isPink() {
        return pink;
    }
    public void setPink(boolean pink) {
        this.pink = pink;
    }
    public boolean isYellow() {
        return yellow;
    }
    public void setYellow(boolean yellow) {
        this.yellow = yellow;
    }
    public boolean isDarkblue() {
        return darkblue;
    }
    public void setDarkblue(boolean darkblue) {
        this.darkblue = darkblue;
    }

    @Override
    public String toString() {
        return "ColorModel{" +
                "red=" + red +
                ", bisque=" + bisque +
                ", blue=" + blue +
                ", gray=" + gray +
                ", green=" + green +
                ", pink=" + pink +
                ", yellow=" + yellow +
                ", darkblue=" + darkblue +
                '}';
    }
}
