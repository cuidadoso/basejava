package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Created by Alejandro on 09.07.2017.
 */
public interface Storage
{
    void clear();
    void save(Resume r);
    void update(Resume r);
    Resume get(String uuid);
    void delete(String uuid);
    Resume[] getAll();
    int size();
}
