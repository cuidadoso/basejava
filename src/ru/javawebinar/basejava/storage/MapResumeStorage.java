package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(final String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(final Object resume) {
        return resume != null;
    }

    @Override
    protected void doSave(final Resume r, final Object resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(final Resume r, final Object resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(final Object resume) {
        map.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Resume doGet(final Object resume) {
        return (Resume) resume;
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
