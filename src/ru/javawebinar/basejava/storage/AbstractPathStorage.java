package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private Path directory;

    protected AbstractPathStorage(final String dir) {
        this.directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if(!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
        if(!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writeble");
        }
    }

    protected abstract void dqWrite(final Resume r, final OutputStream os) throws IOException;

    protected abstract Resume doRead(final InputStream is) throws IOException;

    @Override
    protected Path getSearchKey(final String uuid) {
        return Paths.get(uuid);
    }

    @Override
    protected boolean isExist(final Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(final Resume r, final Path path) {
        try {
            Files.createFile(path);
        } catch(IOException e) {
            throw new StorageException("Couldn't create file " + path.getFileName(), path.getFileName()
                                                                                         .toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected void doUpdate(final Resume r, final Path path) {
        try {
            dqWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch(IOException e) {
            throw new StorageException("File write error", path.getFileName()
                                                               .toString(), e);
        }
    }

    @Override
    protected void doDelete(final Path path) {
        try {
            Files.deleteIfExists(path);
        } catch(IOException e) {
            throw new StorageException("File delete error", path.getFileName()
                                                                .toString());
        }
    }

    @Override
    protected Resume doGet(final Path path) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch(IOException e) {
            throw new StorageException("File reade error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try {
            return Files.list(directory)
                        .map(this::doGet)
                        .collect(Collectors.toList());
        } catch(IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory)
                 .forEach(this::doDelete);
        } catch(IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory)
                              .count();
        } catch(IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }
}
