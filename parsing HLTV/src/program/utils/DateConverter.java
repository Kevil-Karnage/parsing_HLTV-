package program.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DateConverter {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean equalsWithoutTime(Date first, Date second) {

        long firstTime =  first.getTime()/100000000;
        long secondTime = second.getTime()/100000000;

        return firstTime  == secondTime;
/*
        if (first.getYear() == second.getYear()) {
            if (first.getMonth() == second.getMonth()) {
                if (first.getDay() == second.getDay()) {
                    return true;
                }
            }
        }
        return false;*/
    }

    public static Date getCurrentDate() {
        Date date = new Date();
        return date;
    }

    public static String dateToString(Date date) {
        return dateFormat.format(new Date());
    }

    public static Date stringToDate(String s) throws ParseException {
        return dateFormat.parse(s);
    }

    public static Date convert(String str) throws ParseException {

        String[] parts = str.split(" ");

        String day = parseDay(parts[3]);
        Month month = Month.get(parts[2]);
        int year = Integer.parseInt(parts[4]);
        String stringDate = year + "-" + month.number + "-" + day;

        return program.utils.DateConverter.stringToDate(stringDate);
    }

    public static String parseDay(String str) {
        char[] numberInCharArray = Arrays.copyOf(str.toCharArray(), str.length() - 2);
        String day = new String(numberInCharArray);
        if (day.length() == 1) return "0" + day;
        return day;
    }
}

enum Month {
    NONE("NONE", ""),
    JANUARY("January", "01"),
    FEBRUARY("February","02"),
    MARCH("March","03"),
    APRIL("April","04"),
    MAY("May","05"),
    JUNE("June","06"),
    JULY("July","07"),
    AUGUST("August","08"),
    SEPTEMBER("September","09"),
    OCTOBER("October","10"),
    NOVEMBER("November","11"),
    DECEMBER("December","12");


    final String name;
    final String number;

    Month(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public static Month get(String str) {
        for (Month month :
                Month.values()) {
            if (month.name.equals(str)) {
                return month;
            }
        }
        return NONE;
    }
}
