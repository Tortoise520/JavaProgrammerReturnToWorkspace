# Lambda表达式
我来为你详细回顾Java Lambda表达式和函数式接口的核心知识，并提供丰富的实践示例。

## 1. Lambda表达式基础语法

### 语法格式
```java
(parameters) -> expression
// 或
(parameters) -> { statements; }
```

### 基本示例
```java
// 1. 无参数
() -> System.out.println("Hello Lambda")

// 2. 一个参数
x -> x * x

// 3. 多个参数
(x, y) -> x + y

// 4. 带类型声明
(int a, int b) -> a > b
```

## 2. 函数式接口

函数式接口是只有一个抽象方法的接口：

```java
// 自定义函数式接口
@FunctionalInterface
interface MyFunction {
    int operate(int a, int b);
}

// 使用
MyFunction add = (a, b) -> a + b;
System.out.println(add.operate(5, 3)); // 输出: 8
```

## 3. 实践示例

### 示例1：排序列表（Comparator）
```java
import java.util.*;

public class LambdaExamples {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Diana");
        
        // 传统方式 - 匿名内部类
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        
        // Lambda表达式
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
        
        // 更简洁的写法
        names.sort((s1, s2) -> s1.compareTo(s2));
        
        // 方法引用
        names.sort(String::compareTo);
        
        System.out.println("排序后: " + names);
    }
}
```

### 示例2：复杂排序
```java
List<Person> people = Arrays.asList(
    new Person("John", 25),
    new Person("Alice", 30),
    new Person("Bob", 20)
);

// 按年龄排序
people.sort((p1, p2) -> p1.getAge() - p2.getAge());

// 按姓名排序
people.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));

// 多条件排序：先按年龄，再按姓名
people.sort((p1, p2) -> {
    int ageCompare = Integer.compare(p1.getAge(), p2.getAge());
    return ageCompare != 0 ? ageCompare : p1.getName().compareTo(p2.getName());
});
```

### 示例3：线程创建（Runnable）
```java
public class ThreadExamples {
    public static void main(String[] args) {
        // 传统方式 - 匿名内部类
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("传统方式运行的线程");
            }
        });
        
        // Lambda表达式
        Thread thread2 = new Thread(() -> {
            System.out.println("Lambda方式运行的线程");
        });
        
        // 更简洁的Lambda
        Thread thread3 = new Thread(() -> System.out.println("简洁Lambda线程"));
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
```

### 示例4：多个线程示例
```java
// 创建多个线程执行不同任务
for (int i = 0; i < 5; i++) {
    final int threadNumber = i;
    new Thread(() -> {
        System.out.println("线程 " + threadNumber + " 正在执行任务");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程 " + threadNumber + " 完成任务");
    }).start();
}
```

## 4. 常用函数式接口实践

### Predicate 接口
```java
import java.util.function.Predicate;
import java.util.*;

public class PredicateExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Diana", "Eve");
        
        // 传统方式过滤
        List<String> result1 = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("A")) {
                result1.add(name);
            }
        }
        
        // 使用Predicate和Lambda
        Predicate<String> startsWithA = name -> name.startsWith("A");
        Predicate<String> lengthGreaterThan3 = name -> name.length() > 3;
        
        List<String> result2 = filterNames(names, startsWithA);
        List<String> result3 = filterNames(names, lengthGreaterThan3);
        
        // 组合Predicate
        List<String> result4 = filterNames(names, startsWithA.and(lengthGreaterThan3));
        
        System.out.println("以A开头: " + result2);
        System.out.println("长度>3: " + result3);
        System.out.println("以A开头且长度>3: " + result4);
    }
    
    public static List<String> filterNames(List<String> names, Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for (String name : names) {
            if (predicate.test(name)) {
                result.add(name);
            }
        }
        return result;
    }
}
```

### Consumer 接口
```java
import java.util.function.Consumer;
import java.util.*;

public class ConsumerExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob");
        
        // 传统方式
        for (String name : names) {
            System.out.println("Hello, " + name);
        }
        
        // 使用Consumer和Lambda
        Consumer<String> printConsumer = name -> System.out.println("Hello, " + name);
        names.forEach(printConsumer);
        
        // 更简洁的写法
        names.forEach(name -> System.out.println("Welcome, " + name));
        
        // 方法引用
        names.forEach(System.out::println);
    }
}
```

### Function 接口
```java
import java.util.function.Function;
import java.util.*;

public class FunctionExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("john", "alice", "bob");
        
        // 将字符串转换为大写
        Function<String, String> toUpperCase = String::toUpperCase;
        
        // 传统方式转换
        List<String> upperNames1 = new ArrayList<>();
        for (String name : names) {
            upperNames1.add(name.toUpperCase());
        }
        
        // 使用Function转换
        List<String> upperNames2 = transformList(names, toUpperCase);
        
        // 使用Stream API
        List<String> upperNames3 = names.stream()
                                      .map(name -> name.toUpperCase())
                                      .collect(Collectors.toList());
        
        System.out.println("转换后: " + upperNames3);
    }
    
    public static <T, R> List<R> transformList(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }
}
```

## 5. 匿名内部类转换为Lambda

### 转换示例1：事件监听器
```java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventListenerExample {
    public static void main(String[] args) {
        JButton button = new JButton("点击我");
        
        // 匿名内部类方式
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击了！");
            }
        });
        
        // Lambda表达式方式
        button.addActionListener(e -> System.out.println("按钮被点击了！"));
    }
}
```

### 转换示例2：Callable接口
```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        // 匿名内部类方式
        Callable<Integer> task1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 1; i <= 100; i++) {
                    sum += i;
                }
                return sum;
            }
        };
        
        // Lambda表达式方式
        Callable<Integer> task2 = () -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };
        
        Future<Integer> future = executor.submit(task2);
        System.out.println("计算结果: " + future.get());
        
        executor.shutdown();
    }
}
```

## 6. 综合实践项目

```java
import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private double salary;
    private String department;
    
    public Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }
    
    // getters and setters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    
    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, salary=%.2f, department='%s'}",
                           name, age, salary, department);
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("John", 25, 50000, "IT"),
            new Employee("Alice", 30, 60000, "HR"),
            new Employee("Bob", 35, 70000, "IT"),
            new Employee("Diana", 28, 55000, "Finance"),
            new Employee("Eve", 32, 65000, "HR")
        );
        
        // 1. 按工资排序
        System.out.println("按工资排序:");
        employees.sort((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        employees.forEach(System.out::println);
        
        // 2. 过滤IT部门员工
        System.out.println("\nIT部门员工:");
        employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .forEach(System.out::println);
        
        // 3. 按部门分组
        System.out.println("\n按部门分组:");
        Map<String, List<Employee>> byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        byDepartment.forEach((dept, emps) -> {
            System.out.println(dept + ": " + emps);
        });
        
        // 4. 计算平均工资
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.printf("\n平均工资: %.2f\n", avgSalary);
        
        // 5. 创建处理线程
        Thread salaryProcessor = new Thread(() -> {
            double total = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .sum();
            System.out.printf("总工资支出: %.2f\n", total);
        });
        salaryProcessor.start();
    }
}
```

## 关键要点总结

1. **Lambda表达式语法**: `(参数) -> 表达式` 或 `(参数) -> {语句}`
2. **函数式接口**: 只有一个抽象方法的接口
3. **方法引用**: 使用 `::` 运算符进一步简化代码
4. **变量捕获**: Lambda可以访问final或effectively final的外部变量
5. **类型推断**: 编译器可以推断参数类型，通常可以省略

在IntelliJ IDEA中编写这些示例时，注意观察IDE的提示，它通常会建议将匿名内部类转换为Lambda表达式，这是一个很好的学习方式！