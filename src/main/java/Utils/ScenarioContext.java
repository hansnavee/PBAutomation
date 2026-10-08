package Utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static final Map<String, Object> data = new HashMap<>();

    public static void set(String key, Object value) {
        data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) data.get(key);
    }

    public static void clear() {
        data.clear();
    }
}
