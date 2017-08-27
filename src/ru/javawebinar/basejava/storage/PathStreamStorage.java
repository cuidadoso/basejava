package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class PathStreamStorage extends AbstractPathStorage {

    protected PathStreamStorage(final String directory) {
        super(directory);
    }

    @Override
    protected void dqWrite(final Resume r, final OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(final InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)){
            return (Resume) ois.readObject();
        } catch(ClassNotFoundException e) {
            throw new StorageException("Resume reade error", null, e);
        }
    }
}
