package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final Link link;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final String title;

    private final String description;


    public Organization(final String name,
                        final String url,
                        final LocalDate startDate,
                        final LocalDate endDate,
                        final String title,
                        final String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "startDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.link = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Organization that = (Organization) o;

        if(!link.equals(that.link)) {
            return false;
        }
        if(!startDate.equals(that.startDate)) {
            return false;
        }
        if(!endDate.equals(that.endDate)) {
            return false;
        }
        if(!title.equals(that.title)) {
            return false;
        }
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = link.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" + "link=" + link + ", startDate=" + startDate + ", endDate=" + endDate + ", title='" +
               title + '\'' + ", description='" + description + '\'' + '}';
    }
}
