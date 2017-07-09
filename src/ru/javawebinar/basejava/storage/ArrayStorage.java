package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if(size >= STORAGE_LIMIT)
        {
            System.out.println("ERROR(save): Array is full");
        }
        else
        {
            if(getIndex(r.getUuid()) >= 0)
            {
                System.out.println("ERROR(save): Resume is exists already");
            }
            else
            {
                storage[size] = r;
                size++;
            }
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if(index < 0)
        {
            System.out.println("ERROR(update): Resume is not exists");
        }
        else
        {
            storage[index] = r;
        }

    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index < 0)
        {
            System.out.println("ERROR(delete): Resume is not exists");
        }
        else
        {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected int getIndex(String uuid)
    {
        for(int i = 0; i < size; i++)
        {
            if(uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}
