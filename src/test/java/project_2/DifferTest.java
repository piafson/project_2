package project_2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DifferTest {
    private final String filePath1 = "src/test/resources/file1.json";
    private final String filePath2 = "src/test/resources/file2.json";
    private final String filePath3 = "src/test/resources/file1.yml";
    private final String filePath4 = "src/test/resources/file2.yml";
    private final String sample = "{\n"
            + "  - follow: false\n" + "    host: \"hexlet.io\"\n"
            + "  - proxy: \"123.234.53.22\"\n" + "  - timeout: 50\n"
            + "  + timeout: 20\n" + "  + verbose: true\n}";
    @Test
    public void testJson() throws Exception {
        assertEquals(sample, Differ.generate(filePath1, filePath2));
    }
    @Test
    public void testYaml() throws Exception {
        assertEquals(sample, Differ.generate(filePath3, filePath4));
    }
}
