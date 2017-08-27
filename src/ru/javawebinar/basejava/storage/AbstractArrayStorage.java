package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 4;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected boolean isExist(final Integer index) {
        return index >= 0;
    }

    @Override
    protected void doSave(final Resume r, final Integer index) {
        if(size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    protected void doUpdate(final Resume r, final Integer index) {
        storage[index] = r;
    }

    @Override
    protected void doDelete(final Integer index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(final Integer index) {
        return storage[index];
    }
}
