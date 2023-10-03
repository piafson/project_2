package project_2;

import java.io.IOException;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2)
            throws IOException {
        TreeMap<String, Object> mapJson1 = Parser.readObj(filePath1);
        TreeMap<String, Object> mapJson2 = Parser.readObj(filePath2);
        StringBuilder result = new StringBuilder("{\n");
        mapJson1.entrySet().forEach(s -> {
            if (s.getValue().equals(mapJson2.get(s.getKey()))) {
                result.append("    " + s.getKey() + ": " + s.getValue() + "\n");
            } else {
                result.append("  - " + s.getKey() + ": " + s.getValue() + "\n");
            }
        });
        mapJson2.entrySet().forEach(s -> {
            if (!s.getValue().equals(mapJson1.get(s.getKey()))) {
                result.append("  + " + s.getKey() + ": " + s.getValue() + "\n");
            }
        });
        return result + "}";
    }
}
