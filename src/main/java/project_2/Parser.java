package project_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;

public class Parser {
    public static ObjectMapper getObj(String filePath) throws IOException {
        if (filePath.endsWith("json")) {
            return new ObjectMapper();
        }
        if (filePath.endsWith("yaml")) {
            return new YAMLMapper();
        }
        return new ObjectMapper();
    }
    public static TreeMap<String, Object> readObj(String filePath)
            throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String pathStr = new String(Files.readAllBytes(path));
        JsonNode jsonNode1 = getObj(pathStr).readTree(pathStr);
        TreeMap<String, Object> mapObj = new TreeMap<>();
        jsonNode1.fieldNames().forEachRemaining(
                s -> {
                    mapObj.put(s, jsonNode1.get(s));
                });
        return mapObj;
    }
}
