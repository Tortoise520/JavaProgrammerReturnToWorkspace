import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 读取文件并处理
 */
public class FileDemo {
    public static void main(String[] args) {
        try {
            Stream<String> lines = Files.lines(Paths.get("data.txt"));
            List<String> longWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .filter(word -> word.length() > 5)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(longWords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
