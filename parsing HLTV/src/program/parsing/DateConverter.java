package program.parsing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DateConverter {

    public static boolean equalsWithoutTime(Date first, Date second) {
        if (first.getYear() == second.getYear()) {
            if (first.getMonth() == second.getMonth()) {
                if (first.getDay() == second.getDay()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Date convert(String str) throws ParseException {

        String[] parts = str.split(" ");

        Month month = Month.get(parts[2]);
        int day = parseDay(parts[3]);
        int year = Integer.parseInt(parts[4]);
        String stringDate = month.number + "/" + day + "/" + year;

        return new SimpleDateFormat("MM/dd/yyyy").parse(stringDate);
    }

    public static int parseDay(String str) {
        char[] numberInCharArray = Arrays.copyOf(str.toCharArray(), str.length() - 2);
        return Integer.parseInt(new String(numberInCharArray));
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
