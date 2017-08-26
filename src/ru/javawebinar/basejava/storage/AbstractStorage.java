package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(final Object searchKey);

    protected abstract void doSave(final Resume r, final Object searchKey);

    protected abstract void doUpdate(final Resume r, final Object searchKey);

    protected abstract void doDelete(final Object searchKey);

    protected abstract Resume doGet(final Object searchKey);

    protected abstract List<Resume> doCopyAll();

    public void save(Resume r) {
        Object searchKey = getNotExistedKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void update(Resume r) {
        Object searchKey = getExistedKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedKey(uuid);
        return doGet(searchKey);
    }

    private Object getExistedKey(final String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedKey(final String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = doCopyAll();
        Collections.sort(result);
        return result;
    }
}
