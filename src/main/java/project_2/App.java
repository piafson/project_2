package project_2;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    boolean versionHelpRequested;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(filePath1, filePath2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

