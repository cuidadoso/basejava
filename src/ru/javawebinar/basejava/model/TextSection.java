package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends Section {
    private static final long serialVersionUid = 1l;

    private final String content;

    public TextSection(final String content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        TextSection that = (TextSection) o;

        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TextSection{" + "content='" + content + '\'' + '}';
    }
}
