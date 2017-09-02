package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Alexander Pyreev
 */
public interface StreamSerializer {

    void doWrite(final Resume r, OutputStream os) throws IOException;

    Resume doRead(final InputStream is) throws IOException;
}
