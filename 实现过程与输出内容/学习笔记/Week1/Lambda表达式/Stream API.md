# Java Stream API 操作详解

Stream API 是 Java 8 引入的函数式编程特性，用于对集合数据进行高效、声明式的处理。

## 1. 创建 Stream

```java
// 从集合创建
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// 从数组创建
String[] array = {"a", "b", "c"};
Stream<String> stream2 = Arrays.stream(array);

// 使用 Stream.of
Stream<String> stream3 = Stream.of("a", "b", "c");

// 创建数字流
IntStream intStream = IntStream.range(1, 5); // 1,2,3,4
DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3);

// 无限流
Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
Stream<Double> randomStream = Stream.generate(Math::random);
```

## 2. 中间操作

### 过滤操作
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// filter - 过滤
List<Integer> evenNumbers = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList()); // [2, 4, 6, 8, 10]

// distinct - 去重
List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3);
List<Integer> distinct = withDuplicates.stream()
    .distinct()
    .collect(Collectors.toList()); // [1, 2, 3]
```

### 映射操作
```java
List<String> words = Arrays.asList("Java", "Stream", "API");

// map - 转换
List<Integer> wordLengths = words.stream()
    .map(String::length)
    .collect(Collectors.toList()); // [4, 6, 3]

// flatMap - 扁平化
List<List<String>> listOfLists = Arrays.asList(
    Arrays.asList("a", "b"),
    Arrays.asList("c", "d")
);
List<String> flatList = listOfLists.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList()); // ["a", "b", "c", "d"]
```

### 限制和跳过
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// limit - 限制数量
List<Integer> first5 = numbers.stream()
    .limit(5)
    .collect(Collectors.toList()); // [1, 2, 3, 4, 5]

// skip - 跳过元素
List<Integer> skip5 = numbers.stream()
    .skip(5)
    .collect(Collectors.toList()); // [6, 7, 8, 9, 10]
```

### 排序
```java
List<String> names = Arrays.asList("John", "Alice", "Bob", "Diana");

// 自然排序
List<String> sorted = names.stream()
    .sorted()
    .collect(Collectors.toList()); // ["Alice", "Bob", "Diana", "John"]

// 自定义排序
List<String> customSorted = names.stream()
    .sorted((a, b) -> b.compareTo(a)) // 逆序
    .collect(Collectors.toList()); // ["John", "Diana", "Bob", "Alice"]
```

## 3. 终端操作

### 收集结果
```java
List<String> fruits = Arrays.asList("apple", "banana", "orange", "apple");

// 转换为List
List<String> list = fruits.stream().collect(Collectors.toList());

// 转换为Set
Set<String> set = fruits.stream().collect(Collectors.toSet()); // 自动去重

// 转换为Map
Map<String, Integer> map = fruits.stream()
    .distinct()
    .collect(Collectors.toMap(
        fruit -> fruit,
        String::length
    )); // {apple=5, banana=6, orange=6}

// 分组
Map<Integer, List<String>> groupedByLength = fruits.stream()
    .collect(Collectors.groupingBy(String::length));
// {5=[apple], 6=[banana, orange]}

// 分区
Map<Boolean, List<String>> partitioned = fruits.stream()
    .collect(Collectors.partitioningBy(f -> f.length() > 5));
// {false=[apple], true=[banana, orange]}
```

### 查找和匹配
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// anyMatch - 任意匹配
boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0); // true

// allMatch - 全部匹配
boolean allPositive = numbers.stream().allMatch(n -> n > 0); // true

// noneMatch - 无匹配
boolean noNegative = numbers.stream().noneMatch(n -> n < 0); // true

// findFirst - 查找第一个
Optional<Integer> first = numbers.stream().findFirst(); // Optional[1]

// findAny - 查找任意一个
Optional<Integer> any = numbers.stream().findAny(); // Optional[1]
```

### 聚合操作
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// count - 计数
long count = numbers.stream().count(); // 5

// max/min - 最大最小值
Optional<Integer> max = numbers.stream().max(Integer::compareTo); // Optional[5]
Optional<Integer> min = numbers.stream().min(Integer::compareTo); // Optional[1]

// reduce - 归约
Optional<Integer> sum = numbers.stream().reduce(Integer::sum); // Optional[15]
Integer product = numbers.stream().reduce(1, (a, b) -> a * b); // 120

// sum/average - 求和/平均
IntSummaryStatistics stats = numbers.stream()
    .mapToInt(Integer::intValue)
    .summaryStatistics();
// count: 5, sum: 15, min: 1, average: 3.0, max: 5
```
**todo:**
- [ ] 理解归约是什么

## 4. 实际应用示例

### 数据处理示例
```java
class Person {
    private String name;
    private int age;
    private String city;
    
    // 构造方法、getter、setter
    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
    
    // getters...
}

List<Person> people = Arrays.asList(
    new Person("Alice", 25, "New York"),
    new Person("Bob", 30, "London"),
    new Person("Charlie", 28, "New York"),
    new Person("Diana", 22, "Paris")
);

// 找出纽约市年龄大于25岁的人名，按字母排序
List<String> result = people.stream()
    .filter(p -> "New York".equals(p.getCity()))
    .filter(p -> p.getAge() > 25)
    .map(Person::getName)
    .sorted()
    .collect(Collectors.toList()); // ["Charlie"]

// 按城市分组，统计每个城市的平均年龄
Map<String, Double> avgAgeByCity = people.stream()
    .collect(Collectors.groupingBy(
        Person::getCity,
        Collectors.averagingInt(Person::getAge)
    )); // {New York=26.5, London=30.0, Paris=22.0}
```

### 文件处理示例
```java
// 读取文件并处理
try (Stream<String> lines = Files.lines(Paths.get("data.txt"))) {
    List<String> longWords = lines
        .flatMap(line -> Arrays.stream(line.split(" ")))
        .filter(word -> word.length() > 5)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
} catch (IOException e) {
    e.printStackTrace();
}
```

## 5. 性能注意事项

1. **避免在Stream中修改外部状态**
2. **优先使用方法引用**
3. **合理使用并行流**
4. **注意无限流的处理**

```java
// 并行流示例
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> parallelResult = numbers.parallelStream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .collect(Collectors.toList());
```

Stream API 让集合操作更加简洁、可读，并且可以利用多核架构提高处理效率。