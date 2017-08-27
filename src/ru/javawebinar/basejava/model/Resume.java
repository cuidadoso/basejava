package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUid = 1l;
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(final String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(final String uuid, final String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid()
    {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContacts(final ContactType type) {
        return contacts.get(type);
    }

    public Section getSections(final SectionType type) {
        return sections.get(type);
    }

    public void addContact(final ContactType type, final String value) {
        contacts.put(type, value);
    }

    public void addSection(final SectionType type, final Section section) {
        sections.put(type, section);
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Resume resume = (Resume) o;

        if(!uuid.equals(resume.uuid)) {
            return false;
        }
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" + "uuid='" + uuid + '\'' + ", fullName='" + fullName + '\'' + '}';
    }

    @Override
    public int compareTo(final Resume o) {
        return fullName.compareTo(o.fullName);
    }
}
