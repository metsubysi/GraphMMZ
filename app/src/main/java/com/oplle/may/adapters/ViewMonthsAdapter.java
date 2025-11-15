package com.oplle.may.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.oplle.may.CalendarHelper;
import com.oplle.may.R;

import java.util.ArrayList;
import java.util.List;



public class ViewMonthsAdapter<T> extends ArrayAdapter<T> {

    private final Context context;
    private final int layoutResourceId;
    private final T[] data;
    int f;
    private final List<List<String>> monthDaysData = new ArrayList<>();

    public ViewMonthsAdapter(Context context, int layoutResourceId, T[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
        this.f = f;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.monthTextView = row.findViewById(R.id.month_name);
            holder.monthGridView = row.findViewById(R.id.calendar_grid);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        T item = data[position];
        String s = item.toString();
        String[] splitted = s.split(" ");
        int month = Integer.parseInt(splitted[0]);
        int year = Integer.parseInt(splitted[1]);
        holder.monthTextView.setText(CalendarHelper.getMonthName(month) + " " + year + " г.");
        List<CalendarDate> dates = getDates(position);

        CalendarDateAdapter adapter = new CalendarDateAdapter(this.getContext(), dates);
        holder.monthGridView.setAdapter(adapter);
/////////////////////////////////////
        return row;
    }

    static class ViewHolder {
        TextView monthTextView;
        GridView monthGridView;
    }

    @SuppressLint("ResourceType")
    private List<CalendarDate> getDates(int position) {
        int firstDay = CalendarHelper.getDayOfWeekForFirstDay(2024 + (position / 12), position % 12 + 1);
        int numberOfDays = CalendarHelper.getDaysInMonth(2024 + (position / 12), position % 12 + 1);
        int lastMonthNumberOfDays = 31;
        int startDays;
        int textFont;
        if (position > 0) {
            lastMonthNumberOfDays = CalendarHelper.getDaysInMonth(2024 + (position / 12), (position - 1) % 12 + 1);
        }
            List<CalendarDate> items = new ArrayList<>();
            for (int i = 1; i < 8; i++) {
                CalendarDate item = new CalendarDate(CalendarHelper.getDayOfWeekShortName(i), "#000000", Color.TRANSPARENT, 16, Typeface.BOLD);
                items.add(item);
            }
            for (int i = firstDay-2; i > -1; i--) {
                startDays = lastMonthNumberOfDays - i;
                CalendarDate item = new CalendarDate(startDays + "", "#d0d0d0", Color.TRANSPARENT, 16, Typeface.NORMAL);
                items.add(item);
            }
        int workDay = (int) ((CalendarHelper.daysBetween(2024, 1, 2024 + ((position) / 12), (position+1) % 12)) + CalendarDate.numBrigad) % 4;
            for (int i = 1; i <= numberOfDays; i++) {
                CalendarDate item;
                int font = Typeface.NORMAL;
                textFont = Color.TRANSPARENT;
                String textColor = "#000000";//black
                int fontColor = Color.TRANSPARENT;//White
                if (workDay == 0) {
                    fontColor = R.drawable.day_date;
                    textColor = "#000000";
                } else if (workDay == 1) {
                    fontColor = R.drawable.night_date;
                    textColor = "#ffffff";
                }
                if ((2024 + (position / 12) == CalendarHelper.getCurrentYear())
                        && (position % 12 + 1 == CalendarHelper.getCurrentMonth())
                        && (i == CalendarHelper.getCurrentDay())) {
                    font = Typeface.BOLD;
                    textFont = R.drawable.button_normal;
                    textColor = "#ffffff";
                }
                item = new CalendarDate(String.valueOf(i), textColor, fontColor, 16, font, textFont);
                items.add(item);
                workDay = ++workDay % 4;
            }
            for (int i = 42; i >= numberOfDays + firstDay; i--) {
                CalendarDate item = new CalendarDate(String.valueOf(43 - i), "#d0d0d0", Color.TRANSPARENT, 16, Typeface.NORMAL);
                items.add(item);
            }
            return items;

    }
}
