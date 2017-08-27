package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUiidStorage extends AbstractStorage<String> {

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(final String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(final String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected void doSave(final Resume r, final String uuid) {
        map.put(uuid, r);
    }

    @Override
    protected void doUpdate(final Resume r, final String uuid) {
        map.put(uuid, r);
    }

    @Override
    protected void doDelete(final String uuid) {
        map.remove(uuid);
    }

    @Override
    protected Resume doGet(final String uuid) {
        return map.get(uuid);
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
