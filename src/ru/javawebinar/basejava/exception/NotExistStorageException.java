package ru.javawebinar.basejava.exception;

/**
 * Created by Alejandro on 10.07.2017.
 */
public class NotExistStorageException extends StorageException {


    public NotExistStorageException(final String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
