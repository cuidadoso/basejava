package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    private final StreamSerializer serializer;

    protected PathStorage(final String dir, final StreamSerializer serializer) {
        Objects.requireNonNull(dir, "directory must not be null");
        this.directory = Paths.get(dir);
        this.serializer = serializer;
        if(!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
        if(!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writeble");
        }
    }

    @Override
    protected Path getSearchKey(final String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(final Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(final Resume r, final Path path) {
        try {
            Files.createFile(path);
        } catch(IOException e) {
            throw new StorageException("Couldn't create file " + path.getFileName(), getFileName(path), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected void doUpdate(final Resume r, final Path path) {
        try {
            serializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch(IOException e) {
            throw new StorageException("File write error", getFileName(path), e);
        }
    }

    @Override
    protected void doDelete(final Path path) {
        try {
            Files.deleteIfExists(path);
        } catch(IOException e) {
            throw new StorageException("File delete error", getFileName(path));
        }
    }

    @Override
    protected Resume doGet(final Path path) {
        try {
            return serializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch(IOException e) {
            throw new StorageException("File reade error", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFilesList().map(this::doGet)
                             .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch(IOException e) {
            throw new StorageException("Path delete error", e);
        }
    }

    private String getFileName(final Path path) {
        return path.getFileName()
                   .toString();
    }
}
