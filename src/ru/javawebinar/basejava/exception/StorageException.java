package ru.javawebinar.basejava.exception;

/**
 * Created by Alejandro on 10.07.2017.
 */
public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(final String message) {
        this(message, null, null);
    }

    public StorageException(final String message, final String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(final String message, final String uuid, final Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public StorageException(final String message, final Exception e) {
        this(message, null, e);
    }

    public String getUuid() {
        return uuid;
    }
}
