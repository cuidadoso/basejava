package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.DateUtil;
import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUid = 1L;

    private Link homePage;
    private List<Position> positions = new ArrayList<>();

    public Organization() {
    }

    public Organization(final String name, final String url, final Position ... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(final Link homePage, final List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
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

        return (homePage != null ? homePage.equals(that.homePage) : that.homePage == null) &&
               (positions != null ? positions.equals(that.positions) : that.positions == null);
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + (positions != null ? positions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" + "homePage=" + homePage + ", positions=" + positions + '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUid = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        }

        public Position(final int startYear, final Month startMonth, final String title, final String description) {
            this(DateUtil.of(startYear, startMonth), NOW, title, description);
        }

        public Position(final int startYear, final Month startMonth, final int endYear, final Month endMonth,
                        final String title, final String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
        }

        public Position(final LocalDate startDate, final LocalDate endDate, final String title, final String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "startDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description == null ? "" : description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(final Object o) {
            if(this == o) {
                return true;
            }
            if(o == null || getClass() != o.getClass()) {
                return false;
            }

            Position position = (Position) o;

            return startDate.equals(position.startDate) && endDate.equals(position.endDate) &&
                   title.equals(position.title) &&
                   (description != null ? description.equals(position.description) : position.description == null);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Position{" + "startDate=" + startDate + ", endDate=" + endDate + ", title='" + title + '\'' +
                   ", description='" + description + '\'' + '}';
        }
    }
}
