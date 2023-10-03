package project_2;

import java.io.IOException;
import java.util.TreeMap;

public class Differ {
    /*
    public static String generate(String filePath1, String filePath2)
            throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        String json1 = new String(Files.readAllBytes(path1));
        String json2 = new String(Files.readAllBytes(path2));
        //ObjectMapper objectMapper = new ObjectMapper();
        //JsonNode jsonNode1 = objectMapper.readTree(json1);
        //JsonNode jsonNode2 = objectMapper.readTree(json2);


        JsonNode jsonNode1 = Parser.getObj(json1).readTree(json1);
        JsonNode jsonNode2 = Parser.getObj(json2).readTree(json2);


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
        return getString(mapJson1, mapJson2);
    } */

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
