import java.util.*;
import java.util.function.*;

public class CollectionExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // 使用 Lambda 和默认方法
        names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
