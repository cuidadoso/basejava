package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUid = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

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

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
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
        if(!(o instanceof Resume)) {
            return false;
        }

        Resume resume = (Resume) o;

        return (uuid != null ? uuid.equals(resume.uuid) : resume.uuid == null) &&
               (fullName != null ? fullName.equals(resume.fullName) : resume.fullName == null) &&
               (contacts != null ? contacts.equals(resume.contacts) : resume.contacts == null) &&
               (sections != null ? sections.equals(resume.sections) : resume.sections == null);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
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
