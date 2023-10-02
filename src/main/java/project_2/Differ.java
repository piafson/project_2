package project_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filePath1, String filePath2)
            throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        String json1 = new String(Files.readAllBytes(path1));
        String json2 = new String(Files.readAllBytes(path2));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode1 = objectMapper.readTree(json1);
        JsonNode jsonNode2 = objectMapper.readTree(json2);
        StringBuilder result = new StringBuilder("{\n");
        TreeMap<String, Object> mapJson1 = new TreeMap<>();
        TreeMap<String, Object> mapJson2 = new TreeMap<>();
        jsonNode1.fieldNames().forEachRemaining(
                s -> {
                    mapJson1.put(s, jsonNode1.get(s));
                });
        jsonNode2.fieldNames().forEachRemaining(
                s -> {
                    mapJson2.put(s, jsonNode2.get(s));
                });

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
