package com.oplle.may.adapters;

import android.graphics.Color;

public class CalendarDate {
    private String day;            // номер даты (например, 1, 2, 3 и т.д.)
    private String textColor;   // цвет текста (например, "red", "blue" и т.д.)
    private int fontColor;   // цвет фона (например, "white", "black" и т.д.)
    private int font; // стиль шрифта
    private int fontSize; // размер шрифта
    private int textFont = Color.TRANSPARENT; // цвет фона textview
    public static int numBrigad = 0;


    // Конструктор
    public CalendarDate(String day, String textColor, int fontColor, int fontSize, int font) {
        this.day = day;
        this.textColor = textColor;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.font = font;
    }
    public CalendarDate(String day, String textColor, int fontColor, int fontSize, int font, int textFont) {
        this.day = day;
        this.textColor = textColor;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.font = font;
        this.textFont = textFont;
    }

    // Геттеры и сеттеры
    public static void setNumBrigad(int numBrigad) {
        CalendarDate.numBrigad = numBrigad;
    }
    public int getTextFont() {
        return textFont;
    }
    public String getDay() {
        return day;
    }

    public int getFont() {
        return font;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public int getFontColor() {
        return fontColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public String toString() {
        return "CalendarDate{" +
                "day=" + day +
                ", textColor='" + textColor + '\'' +
                ", fontColor='" + fontColor + '\'' +
                ", fontSize=" + fontSize +
                '}';
    }
}