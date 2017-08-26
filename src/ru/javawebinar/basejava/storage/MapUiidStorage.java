package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUiidStorage extends AbstractStorage {

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(final String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(final Object uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    protected void doSave(final Resume r, final Object uuid) {
        map.put((String) uuid, r);
    }

    @Override
    protected void doUpdate(final Resume r, final Object uuid) {
        map.put((String) uuid, r);
    }

    @Override
    protected void doDelete(final Object uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected Resume doGet(final Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
