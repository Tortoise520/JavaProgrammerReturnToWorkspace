import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Diana", "Eve");
        //传统过滤方式
        List<String> result1 = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("A")) {
                result1.add(name);
            }
        }
        System.out.println(result1);
        //使用Predicate过滤
        Predicate<String> startWithA = name -> name.startsWith("A");
        Predicate<String> lengthGreaterThan3 = name -> name.length() > 3;
        
        List<String> result2 = filterNames(names, startWithA);
        System.out.println(result2);
        List<String> result3 = filterNames(names, lengthGreaterThan3);
        System.out.println(result3);
        List<String> result4 = filterNames(names, startWithA.and(lengthGreaterThan3));
        System.out.println(result4);
        
    }
    public static List<String> filterNames(List<String> names, Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for(String name : names) {
            if(predicate.test(name))
                result.add(name);
        }
        return result;
    }
}
