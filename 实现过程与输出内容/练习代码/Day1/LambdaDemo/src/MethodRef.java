import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MethodRef {
    public static void main(String[] args) {
        // Method reference
        // 1. Static method（Class::staticMethod）
        // Lambda 表达式
        Function<String, Integer> converter1 = s -> Integer.parseInt(s);
        
        // 方法引用
        Function<String, Integer> converter2 = Integer::parseInt;
        
        System.out.println(converter2.apply("123")); // 输出：123
        // Lambda 表达式
        Function<Integer, Integer> abs1 = x -> Math.abs(x);
        // 方法引用
        Function<Integer, Integer> abs2 = Math::abs;
        System.out.println(abs2.apply(-123)); // 输出：123
        
        // 2. Instance method（instance::instanceMethod）

        /*
        设置一个名字格式化规则
         */
        String prefix = "Mr. ";
        // Lambda 表达式
        Function<String, String> addPrefix1 = name -> prefix.concat(name);

        // 方法引用
        Function<String, String> addPrefix2 = prefix::concat;
    
        System.out.println(addPrefix2.apply("Smith")); // 输出：Mr. Smith
    
        // 3. Class method（Class::instanceMethod）
        /*
        比较两个字符串
         */
        //lambda表达式
        BiFunction<String, String, Boolean> com1 = (s1, s2) -> s1.equals(s2);
        //方法引用
        BiFunction<String, String, Boolean> com2 = String::equals;
        System.out.println(com2.apply("hello", "hello"));//true
        
        /*
        获取字符串长度
         */
        List<String> names = Arrays.asList("Alice", "Bob", "Marry");
        //lambda表达式
        List<Integer> l1 = names.stream().map(s -> s.length()).collect(Collectors.toList());
        //方法引用
        List<Integer> l2 = names.stream().map(String::length).collect(Collectors.toList());
        System.out.println(l2);//[5, 3, 5]
        
        
        // 4. Constructor（Class::new）
        /*
        创建一个字符串列表
         */
        //lambda表达式
        Supplier<List<String>> s1 = () -> new ArrayList<>();
        //方法引用
        Supplier<List<String>> s2 = ArrayList::new;
        List<String> list = s2.get();
        /*
        使用带参数的构造方法
         */
        //lambda表达式
        Function<String, Integer> c1 = s -> new Integer(s);
        //方法引用
        Function<String, Integer> c2 = Integer::new;
        System.out.println(c2.apply("456"));//456
        /*
        引用有多个参数的构造方法
         */
        //lambda表达式
        BiFunction<String, Integer, Person> pc1 = (name, age) -> new Person(name, age);
        //方法引用
        BiFunction<String, Integer, Person> pc2 = Person::new;
        Person p = pc2.apply("Tom", 25);
        
    }
}
