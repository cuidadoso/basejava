package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getSearchKey(final String uuid) {
        for(int i = 0; i < list.size(); i++) {
            if(uuid.equals(list.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(final Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(final Resume r, final Object searchKey) {
        list.add(r);
    }

    @Override
    protected void doUpdate(final Resume r, final Object searchKey) {
        list.set((Integer) searchKey, r);
    }

    @Override
    protected void doDelete(final Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(final Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}
