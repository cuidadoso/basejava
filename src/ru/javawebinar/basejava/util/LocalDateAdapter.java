package ru.javawebinar.basejava.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * @author Alexander Pyreev
 */
public class LocalDateAdapter extends XmlAdapter<String , LocalDate> {
    @Override
    public LocalDate unmarshal(final String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(final LocalDate v) throws Exception {
        return v.toString();
    }
}
