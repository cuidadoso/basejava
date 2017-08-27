package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    protected abstract Resume doRead(final File file) throws IOException;

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
        } catch(IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected void doUpdate(final Resume r, final File file) {
        try {
            dqWrite(r, file);
        } catch(IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(final File file) {
        if(!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected Resume doGet(final File file) {
        try {
            return doRead(file);
        } catch(IOException e) {
            throw new StorageException("File reade error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        if(files == null) {
            throw new StorageException("Directory read error", null);
        }
        return Arrays.stream(files)
                     .map(this::doGet)
                     .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if(files != null) {
            Arrays.stream(files)
                  .forEach(this::doDelete);
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if(list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }
}
