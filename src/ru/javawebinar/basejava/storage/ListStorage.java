package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

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
    protected boolean isExist(final Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(final Resume r, final Integer searchKey) {
        list.add(r);
    }

    @Override
    protected void doUpdate(final Resume r, final Integer searchKey) {
        list.set(searchKey, r);
    }

    @Override
    protected void doDelete(final Integer searchKey) {
        list.remove((searchKey).intValue());
    }

    @Override
    protected Resume doGet(final Integer searchKey) {
        return list.get(searchKey);
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
