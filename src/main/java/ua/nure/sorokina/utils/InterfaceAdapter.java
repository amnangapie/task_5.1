package ua.nure.sorokina.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

/*
 * This class provides functionality for deserialization with inheritance
 * as in Gson it is not provided out of box
 */
public class InterfaceAdapter<T>
        implements JsonSerializer<T>, JsonDeserializer<T> {

    @Override
    public JsonElement serialize(T object, Type type,
                                 JsonSerializationContext context) {
        final JsonObject wrapper = new JsonObject();
        wrapper.addProperty("type", object.getClass().getName());
        wrapper.add("data", new Gson().toJsonTree(object, type));
        return wrapper;
    }

    @Override
    public T deserialize(JsonElement json, Type type,
                         JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject wrapper = (JsonObject) json;
        final JsonElement typeName = get(wrapper, "type");
        final JsonElement data = get(wrapper, "data");
        final Type actualType = typeForName(typeName);
        return context.deserialize(data, actualType);
    }

    private Type typeForName(final JsonElement typeElem) {
        try {
            return Class.forName(typeElem.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    private JsonElement get(final JsonObject wrapper, String memberName) {
        final JsonElement element = wrapper.get(memberName);
        if (element == null) {
            throw new JsonParseException("no '" + memberName + "' member found "
                    + "in what was expected to be an interface wrapper");
        }
        return element;
    }
}