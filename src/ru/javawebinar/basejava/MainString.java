package ru.javawebinar.basejava;

/**
 * Created by Alejandro on 10.07.2017.
 */
public class MainString
{
    public static void main(String[] args)
    {
        String[] strArray = new String[]{"1", "2", "3"};
        StringBuilder result = new StringBuilder();
        for(String str : strArray) {
            result.append(str).append(", ");
        }
        System.out.println(result);
    }
}
