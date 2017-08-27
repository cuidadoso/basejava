package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000,1,1);
    public static LocalDate of(int year, Month mounth) {
        return LocalDate.of(year, mounth, 1);
    }
}
