package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static LocalDate of(int year, Month mounth) {
        return LocalDate.of(year, mounth, 1);
    }
}
