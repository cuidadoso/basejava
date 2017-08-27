package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(final String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(final Resume resume) {
        return resume != null;
    }

    @Override
    protected void doSave(final Resume r, final Resume resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(final Resume r, final Resume resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(final Resume resume) {
        map.remove(resume.getUuid());
    }

    @Override
    protected Resume doGet(final Resume resume) {
        return resume;
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
