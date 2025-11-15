package com.oplle.may;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarHelper {

    public static int getDayOfWeekForFirstDay(int year, int month) {
        // Создаем объект Calendar для первого дня заданного месяца и года
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Преобразуем Calendar.DAY_OF_WEEK в числовое значение, где Понедельник = 1
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return 7;
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
            default:
                throw new IllegalArgumentException("Неверный день недели");
        }
    }

    public static int getDaysInMonth(int year, int month) {
        // Создаем объект Calendar для первого дня заданного месяца и года
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);

        // Получаем количество дней в этом месяце
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static long daysBetween(int year1, int month1, int year2, int month2) {
        // Создаем LocalDate для первой и второй даты
        if (month2 == 0) {month2 = 12;}
        LocalDate date1 = null; // первое число месяца
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date1 = LocalDate.of(year1, month1, 1);
        }
        LocalDate date2 = null; // первое число месяца
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date2 = LocalDate.of(year2, month2, 1);
        }

        // Считаем количество дней между двумя датами
        long daysBetween = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            daysBetween = ChronoUnit.DAYS.between(date1, date2);
        }

        return daysBetween;
    }

    public static String getDayOfWeekShortName(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return "Пн";
            case 2:
                return "Вт";
            case 3:
                return "Ср";
            case 4:
                return "Чт";
            case 5:
                return "Пт";
            case 6:
                return "Сб";
            case 7:
                return "Вс";
            default:
                throw new IllegalArgumentException("День недели должен быть от 1 до 7");
        }
    }

        public static int calculateMonthsSince2000() {
            // Создаем объект YearMonth для января 2000 года
            YearMonth start = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                start = YearMonth.of(2024, 1);
            }

            // Получаем текущий год и месяц
            YearMonth current = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                current = YearMonth.now();
            }

            // Вычисляем количество месяцев между start и current
            long monthsBetween = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                monthsBetween = ChronoUnit.MONTHS.between(start, current);
            }

            // Преобразуем результат к типу int и возвращаем
            return (int) monthsBetween;
        }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    // Метод для получения текущего месяца (месяцы начинаются с 0, поэтому добавляем 1)
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    // Метод для получения текущего дня месяца
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case 1:
                monthName = "Январь";
                break;
            case 2:
                monthName = "Февраль";
                break;
            case 3:
                monthName = "Март";
                break;
            case 4:
                monthName = "Апрель";
                break;
            case 5:
                monthName = "Май";
                break;
            case 6:
                monthName = "Июнь";
                break;
            case 7:
                monthName = "Июль";
                break;
            case 8:
                monthName = "Август";
                break;
            case 9:
                monthName = "Сентябрь";
                break;
            case 10:
                monthName = "Октябрь";
                break;
            case 11:
                monthName = "Ноябрь";
                break;
            case 12:
                monthName = "Декабрь";
                break;
            default:
                monthName = "Неверный номер месяца";
                break;
        }
        return monthName;
    }
}
