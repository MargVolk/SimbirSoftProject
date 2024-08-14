package ru.margarite.volkova.utils.dateUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateParse {
    private final List<Integer> dateList;
    private final Calendar calendar = Calendar.getInstance();

    public DateParse(String date){
        dateList = Arrays.stream(date.split("\\.")).map(Integer::parseInt).toList();
        calendar.set(dateList.get(2), dateList.get(1) - 1, dateList.get(0));
    }

    public String getYear(){
        return String.valueOf(dateList.get(2));
    }

    public String getMonth(){
        return  String.valueOf(dateList.get(1) - 1);
    }

    public int getDay(){
        return  dateList.get(0);
    }

    public String getLongNameMonth(){
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
    }

    public String getDateWithFormat(String pattern){
        return new SimpleDateFormat(pattern, Locale.UK).format(calendar.getTime());
    }
}
