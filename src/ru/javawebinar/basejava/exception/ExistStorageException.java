package ru.javawebinar.basejava.exception;

/**
 * Created by Alejandro on 10.07.2017.
 */
public class ExistStorageException extends StorageException {


    public ExistStorageException(final String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}
