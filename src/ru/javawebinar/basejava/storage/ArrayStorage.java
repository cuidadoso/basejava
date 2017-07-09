package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for(int i = 0; i < size; i++)
        {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for(int i = 0; i < size; i++)
        {
            if(uuid.equals(storage[i].getUuid()))
                return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        int delPos = -1;
        for(int i = 0; i < size; i++)
        {
            if(uuid.equals(storage[i].getUuid()))
            {
                delPos = i;
                break;
            }
        }
        if(delPos >= 0) {
            for(int i = delPos; i < size; i++)
            {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size());
        return result;
    }

    public int size() {
        return size;
    }
}
