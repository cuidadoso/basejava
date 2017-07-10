package ru.javawebinar.basejava.storage;

import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by Alejandro on 10.07.2017.
 */
public class ArrayStorageTest extends AbstractArrayStorageTest {
    @Override
    protected Storage createStorage() {
        return new ArrayStorage();
    }

    @Before
    public void init() throws Exception {
        setUp();
    }
}