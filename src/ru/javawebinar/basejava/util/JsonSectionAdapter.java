package ru.javawebinar.basejava.util;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author Alexander Pyreev
 */
public class JsonSectionAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public T deserialize(final JsonElement json, final Type type, final JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();

        try {
            Class clazz = Class.forName(className);
            return context.deserialize(jsonObject.get(INSTANCE), clazz);
        } catch(ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(final T section, final Type type, final JsonSerializationContext context) {
        JsonObject retValue = new JsonObject();
        retValue.addProperty(CLASSNAME, section.getClass().getName());
        JsonElement element = context.serialize(section);
        retValue.add(INSTANCE, element);
        return retValue;
    }
}
