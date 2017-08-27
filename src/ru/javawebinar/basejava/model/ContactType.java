package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Phone"),
    MOBILE("Mobile"),
    HOME_PHONE("Home phone"),
    SKYPE("Skype"),
    MAIL("E-mail"),
    LINKEDIN("LinkedIn profile"),
    GITHUB("GitHub profile"),
    STACKOVERFLOW("Stackoverflow profile"),
    HOME_PAGE("Home page");

    private final String title;

    ContactType(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
