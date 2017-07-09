package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage
{
    protected static final int STORAGE_LIMIT = 3;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract int getIndex(String uuid);
    protected abstract void fillDeletedElement(int index);
    protected abstract void insertElement(Resume r, int index);

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index < 0)
        {
            System.out.println("ERROR(get): Resume is not exists");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if(index >= 0)
        {
            System.out.println("ERROR(save): Resume is exists already");
        }
        else if(size >= STORAGE_LIMIT)
        {
            System.out.println("ERROR(save): Array is full");
        }
        else {
            insertElement(r, index);
            size++;
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
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }
}
