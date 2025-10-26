import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    
    /**
     * 创建流
     */
    static void createStream() {
        //从集合创建
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream1 = list.stream();
        //从数组创建
        String strs[] = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(strs);
        //使用Stream.of创建
        Stream<String> stream3 = Stream.of("a", "b", "c");
        //数字流
        IntStream intStream = IntStream.range(1, 5);
        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3);
        //无限流
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        Stream<Double> randomStream = Stream.generate(Math::random);
    }
    
    /**
     * 过滤操作 filter
     */
    static void filterStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //过滤，获取偶数值
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        //去重
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3);
        List<Integer> distinct = withDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
    }
    
    /**
     * 映射操作 map
     */
    static void mapStream() {
        List<String> words = Arrays.asList("Java", "Stream", "API");
        //转换
        List<Integer> lengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        //扁平化
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d")
        );
        List<String> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
    
    /**
     * 限制与跳过
     */
    static void limitAndSkip() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //取前五个数
        List<Integer> first5 = numbers.stream()
                .limit(5)
                .collect(Collectors.toList());
        //跳过前五个数
        List<Integer> skip5 = numbers.stream()
                .skip(5)
                .collect(Collectors.toList());
    }
    
    /**
     * 排序
     */
    static void sortedStream() {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Diana");
        //自然排序
        List<String> sorted = names.stream()
                .sorted()
                .collect(Collectors.toList());
        //自定义排序
        List<String> cumstomSorted = names.stream()
                .sorted((a, b) -> b.compareTo(a))//逆序
                .collect(Collectors.toList());
    }
    
    /**
     * 收集结果
     */
    static void collectStream() {
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "apple");
        //转换为list
        List<String> list = fruits.stream().collect(Collectors.toList());
        //转换为set
        Set<String> set = fruits.stream().collect(Collectors.toSet());//自动去重
        //转换为Map
        Map<String, Integer> map = fruits.stream()
                .distinct()
                .collect(Collectors.toMap(fruit -> fruit, String::length));
        // {apple=5, banana=6, orange=6}
        
        //分组
        Map<Integer, List<String>> groupByLength = fruits.stream()
                .collect(Collectors.groupingBy(String::length));
        // {5=[apple], 6=[banana, orange]}
        
        //分区
        Map<Boolean, List<String>>  partitioned = fruits.stream()
                .collect(Collectors.partitioningBy(n -> n.length() > 5));
        // {false=[apple], true=[banana, orange]}
    }
    
    /**
     * 查找和匹配
     */
    static void searchAndMatch() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //anyMatch
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        //allMatch
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        //noneMatch
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
        //findFirst
        Optional<Integer> first = numbers.stream().findFirst();//Optional[1]
        //findAny
        Optional<Integer> any = numbers.stream().findAny();//Optional[1]
    }
    
    /**
     * 聚合操作
     */
    static void aggregateStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //count
        long count = numbers.stream().count();
        // max/min
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        //reduce 归约
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        Integer product = numbers.stream().reduce(1, (a, b) -> a * b);
        
    }
}