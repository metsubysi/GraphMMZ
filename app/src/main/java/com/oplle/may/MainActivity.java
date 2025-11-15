package com.oplle.may;

import static com.oplle.may.CalendarHelper.calculateMonthsSince2000;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.oplle.may.adapters.CalendarDate;
import com.oplle.may.adapters.ViewMonthsAdapter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public ListView listView;
    public ViewMonthsAdapter<MmzCalendar> adapter;
    private Button[] buttons = new Button[4];
    private static final String PREFS_NAME = "my_prefs";
    private static final String KEY_VARIABLE = "my_variable";
    @SuppressLint("UseCompatLoadingForDrawables")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.months_list);
        // Загружаем сохраненное значение переменной хранящей номер бригады
        int savedVariable = loadVariable();

        // Если переменная еще не была установлена, устанавливаем ее по умолчанию
        if (savedVariable == -1) {
            savedVariable = 0; // По умолчанию устанавливаем 0
            saveVariable(savedVariable); // Сохраняем значение
        }
        CalendarDate.numBrigad = loadVariable();
        buttons[3] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[0] = findViewById(R.id.button3);
        buttons[2] = findViewById(R.id.button4);

// определяем массив месяцевp
        final MmzCalendar[] monthNames = new MmzCalendar[120];
        int numMonths = 0;
        for (int i = 2024; i < 2034; i++) {
            for (int j = 1; j < 13; j++) {
                monthNames[numMonths] = new MmzCalendar(j, i);
                numMonths++;
            }
        }

// используем адаптер данных

        adapter = new ViewMonthsAdapter(this,
                R.layout.calendar_layout, monthNames);
        listView.setAdapter(adapter);
        listView.setSelection(calculateMonthsSince2000());

        Map<Integer, Runnable> buttonClickHandlers = new HashMap<>();
        buttonClickHandlers.put(R.id.button1, () -> CalendarDate.numBrigad = 3);
        buttonClickHandlers.put(R.id.button2, () -> CalendarDate.numBrigad = 1);
        buttonClickHandlers.put(R.id.button3, () -> CalendarDate.numBrigad = 0);
        buttonClickHandlers.put(R.id.button4, () -> CalendarDate.numBrigad = 2);
        View.OnClickListener buttonClickListener = view -> {
            Runnable handler = buttonClickHandlers.get(view.getId());
            adapter = new ViewMonthsAdapter<>(this,
                    R.layout.calendar_layout, monthNames);
            listView.setAdapter(adapter);
            for(int i = 0; i < 4; i++) {
                buttons[i].setBackgroundColor(Color.LTGRAY);
            }
            view.setBackgroundColor(Color.BLACK);
            listView.setSelection(calculateMonthsSince2000());
            view.setTag(view);

            if (handler != null) {
                handler.run();
            }
        };

        for (int i = 0; i < 4; i++) {
            buttons[i].setBackgroundColor(Color.LTGRAY);
            if (i == loadVariable()) {
                buttons[i].setBackgroundColor(Color.BLACK);
            }
            buttons[i].setOnClickListener(buttonClickListener);
        }
    }

    // Метод для сохранения переменной в SharedPreferences
    private void saveVariable(int value) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_VARIABLE, value);
        editor.apply();
    }

    // Метод для загрузки переменной из SharedPreferences
    private int loadVariable() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_VARIABLE, -1); // Возвращаем -1 если переменная не найдена
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveVariable(CalendarDate.numBrigad);

    }
}