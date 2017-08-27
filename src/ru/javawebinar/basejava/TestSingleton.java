package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.SectionType;

import java.util.Arrays;

public class TestSingleton {
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        if(instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();

        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance);
        System.out.println(instance.ordinal());
        Arrays.stream(SectionType.values())
              .forEach(t -> System.out.println(t.getTitle()));
    }

    public enum Singleton {
        INSTANCE
    }
}
