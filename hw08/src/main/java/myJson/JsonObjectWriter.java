package myJson;


import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class JsonObjectWriter {
    public String toJson(Object obj) {
        JsonValue jsonValue = toJsonValue(obj);
        return jsonValue.toString();
    }
    private JsonValue toJsonValue(Object obj) {
        if(obj == null) {
            return JsonValue.NULL;
        }
        Class<?> clazz = obj.getClass();

        if (clazz == String.class) return toJsonString((String) obj);
        if (clazz == int.class || clazz == Integer.class) return toJsonInt((Integer) obj);
        if (clazz == boolean.class || clazz == Boolean.class) return toJsonBoolean((Boolean) obj);
        if (clazz == double.class || clazz == Double.class) return toJsonDouble((Double) obj);
        if (clazz.isArray()) return toJsonArray(obj);
        if (obj instanceof Collection<?>) return toJsonCollection((Collection<?>) obj);

        return toJsonObject(obj);

    }

    private JsonValue toJsonString(String value) {
        return Json.createValue(value);
    }

    private JsonValue toJsonInt(Integer value) {
        return Json.createValue(value);
    }

    private JsonValue toJsonBoolean(Boolean value) {
        return (value) ? JsonValue.TRUE : JsonValue.FALSE;
    }

    private JsonValue toJsonDouble(Double value) {
        return Json.createValue(value);
    }

    private JsonValue toJsonArray(Object obj) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            arrayBuilder.add(toJsonValue(Array.get(obj, i)));
        }
        return arrayBuilder.build();
    }

    private JsonValue toJsonCollection(Collection<?> collection) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object element : collection) {
            arrayBuilder.add(toJsonValue(element));
        }
        return arrayBuilder.build();
    }

    private JsonValue toJsonObject(Object obj) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                builder.add(field.getName(), toJsonValue(value));
            } catch (Exception e) {
                throw new RuntimeException(
                        "Ошибка сериализации поля " + field.getName() +
                                " в классе " + clazz.getSimpleName(), e);
            }
        }
        return builder.build();
    }
}
