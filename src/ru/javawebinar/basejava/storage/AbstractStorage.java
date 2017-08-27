package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(final SK searchKey);

    protected abstract void doSave(final Resume r, final SK searchKey);

    protected abstract void doUpdate(final Resume r, final SK searchKey);

    protected abstract void doDelete(final SK searchKey);

    protected abstract Resume doGet(final SK searchKey);

    protected abstract List<Resume> doCopyAll();

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getNotExistedKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getExistedKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedKey(uuid);
        return doGet(searchKey);
    }

    private SK getExistedKey(final String uuid) {
        LOG.info("Get exist key " + uuid);
        SK searchKey = getSearchKey(uuid);
        if(!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedKey(final String uuid) {
        LOG.info("Get not exist key " + uuid);
        SK searchKey = getSearchKey(uuid);
        if(isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all");
        List<Resume> result = doCopyAll();
        Collections.sort(result);
        return result;
    }
}
