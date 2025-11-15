package com.oplle.may.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oplle.may.R;

import java.util.List;

public class CalendarDateAdapter extends BaseAdapter {
    private Context context;
    private List<CalendarDate> dates;

    public CalendarDateAdapter(Context context, List<CalendarDate> dates) {
        this.context = context;
        this.dates = dates;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_item_layout, parent, false);
        }
        CalendarDate date = dates.get(position);

        TextView textView = convertView.findViewById(R.id.date);
        LinearLayout backLayout = convertView.findViewById(R.id.back_layout);
        textView.setText(String.valueOf(date.getDay()));
        textView.setTextColor(Color.parseColor(date.getTextColor()));
        backLayout.setBackgroundResource(date.getFontColor());
        textView.setTextSize(date.getFontSize());
        textView.setTypeface(textView.getTypeface(), date.getFont());
        textView.setBackgroundResource(date.getTextFont());

        return convertView;
    }
}

