package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_SIZE = 3;
    private Resume[] storage = new Resume[MAX_SIZE];
    private int size = 0;

    public void clear() {
        for(int i = 0; i < size; i++)
        {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if(size >= MAX_SIZE)
        {
            System.out.println("ERROR(save): Array is full");
        }
        else
        {
            int index = getIndex(r.getUuid());
            if(index >= 0)
            {
                System.out.println("ERROR(save): Resume is exists already");
                return;
            }
            storage[size] = r;
            size++;
        }
    }

    public void update(String uuid, Resume r) {
        int index = getIndex(uuid);
        if(index <0)
        {
            System.out.println("ERROR(update): Resume is not exists");
            return;
        }
        storage[index] = r;
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
        int index = getIndex(uuid);
        if(index < 0)
        {
            System.out.println("ERROR(delete): Resume is not exists");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid)
    {
        int result = -1;
        for(int i = 0; i < size; i++)
        {
            if(uuid.equals(storage[i].getUuid()))
                result = i;
        }
        return result;
    }
}
