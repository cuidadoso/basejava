package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".gitignore";
        File file = new File(filePath);
        File dir = new File("src/ru/javawebinar/basejava");
        try {
            System.out.println(file.getCanonicalPath());
            System.out.println(dir.isDirectory());
            System.out.println(dir.getCanonicalPath());
        } catch(IOException e) {
            throw new RuntimeException("Error", e);
        }
        String[] list = dir.list();
        if(list != null) {
            Arrays.stream(list).forEach(System.out::println);
        }

        try (FileInputStream fis = new FileInputStream(filePath)){
            System.out.println(fis.read());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
