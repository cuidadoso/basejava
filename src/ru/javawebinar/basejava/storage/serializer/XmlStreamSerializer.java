package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Alexander Pyreev
 */
public class XmlStreamSerializer implements StreamSerializer {

    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class, OrganizationSection.class,
                                  TextSection.class, ListSection.class, Organization.Position.class);
    }

    @Override
    public void doWrite(final Resume r, final OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)){
            xmlParser.marshall(r, writer);
        }
    }

    @Override
    public Resume doRead(final InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)){
            return xmlParser.unmarshall(reader);
        }
    }
}
