package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private final File directory;

    protected AbstractFileStorage(final File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if(!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if(!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeble");
        }
        this.directory = directory;
    }

    protected abstract void dqWrite(final Resume r, final File file) throws IOException;

    @Override
    protected File getSearchKey(final String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(final File file) {
        return file.exists();
    }

    @Override
    protected void doSave(final Resume r, final File file) {
        try {
            file.createNewFile();
            dqWrite(r, file);
        } catch(IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(final Resume r, final File file) {

    }

    @Override
    protected void doDelete(final File file) {

    }

    @Override
    protected Resume doGet(final File file) {
        return null;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
