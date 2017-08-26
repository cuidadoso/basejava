package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

/**
 * Created by Alejandro on 10.07.2017.
 */
public abstract class AbstractStorageTest {

    protected final Storage storage;

    protected AbstractStorageTest(final Storage storage) {
        this.storage = storage;
    }

    private static final String UUID1 = "uuid1";
    private static final Resume RESUME_1;
    private static final String UUID2 = "uuid2";
    private static final Resume RESUME_2;
    private static final String UUID3 = "uuid3";
    private static final Resume RESUME_3;
    private static final String UUID4 = "uuid4";
    protected static final Resume RESUME_4;
    private static final String UUID5 = "uuid5";
    protected static final Resume RESUME_5;

    static {
        RESUME_1 = new Resume(UUID1);
        RESUME_2 = new Resume(UUID2);
        RESUME_3 = new Resume(UUID3);
        RESUME_4 = new Resume(UUID4);
        RESUME_5 = new Resume(UUID5);

    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        Resume resume = storage.get(UUID1);
        assertEquals(UUID1, resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        Resume resume = storage.get(UUID4);
        assertEquals(UUID4, resume.getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_3);
    }

    @Test
    public void update() throws Exception {
        storage.update(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_4);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID3);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID4);
    }
}