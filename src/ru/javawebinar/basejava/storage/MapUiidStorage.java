package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapUiidStorage extends AbstractStorage {

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(final String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(final Object searchKey) {
        return false;
    }

    @Override
    protected void doSave(final Resume r, final Object searchKey) {

    }

    @Override
    protected void doUpdate(final Resume r, final Object searchKey) {

    }

    @Override
    protected void doDelete(final Object searchKey) {

    }

    @Override
    protected Resume doGet(final Object searchKey) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
