package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    private static final Resume R1;
    private static final String UUID2 = "uuid2";
    private static final Resume R2;
    private static final String UUID3 = "uuid3";
    private static final Resume R3;
    private static final String UUID4 = "uuid4";
    protected static final Resume R4;
    private static final String UUID5 = "uuid5";
    protected static final Resume R5;

    static {
        R1 = new Resume(UUID1, "Name1");
        R2 = new Resume(UUID2, "Name2");
        R3 = new Resume(UUID3, "Name3");
        R4 = new Resume(UUID4, "Name4");
        R5 = new Resume(UUID5, "Name5");

        R1.addContact(ContactType.MAIL, "mail@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievment11", "Achievment12", "Achievment13"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Qualification11", "Qualification12", "Qualification13"));
        R1.addSection(SectionType.EXPERIENCE,
                      new OrganizationSection(
                              new Organization("Organization11", "http://Organization11.com",
                                               new Organization.Position(2005, Month.JANUARY, "position1", "conteny1"),
                                               new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "conteny2"))));
        R1.addSection(SectionType.EDUCATION,
                      new OrganizationSection(
                              new Organization("Institute", null,
                                               new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                               new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "facultet")),
                              new Organization("Organization2", "http://Organization12.com")));

        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R2.addSection(SectionType.EXPERIENCE,
                      new OrganizationSection(
                              new Organization("Organization21", "http://Organization21.com",
                                               new Organization.Position(2015, Month.JANUARY, "position22", "conteny22"))));

    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
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
    public void getAllSorted() throws Exception {
        List<Resume> resumes = storage.getAllSorted();
        assertEquals(3, resumes.size());
        assertEquals(Arrays.asList(R1, R2, R3), resumes);
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        Resume resume = storage.get(UUID4);
        assertEquals(UUID4, resume.getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R3);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID3, "New Name");
        storage.update(newResume);
        assertTrue(Objects.equals(newResume.getUuid(), storage.get(UUID3)
                                                              .getUuid()));
        assertTrue(Objects.equals(newResume.getFullName(), storage.get(UUID3)
                                                              .getFullName()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(R4);
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