import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonDemo {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, "New York"),
                new Person("Bob", 30, "London"),
                new Person("Charlie", 28, "New York"),
                new Person("Diana", 22, "Paris")
        );
        //找出纽约市年龄大于25岁的人名，按字母排序
        List<String> result = people.stream()
                .filter(person -> "New York".equals(person.getCity()))
                .filter(person -> person.getAge() > 25)
                .map(Person::getName)
                .sorted()
                .collect(Collectors.toList());// ["Charlie"]
        //按城市分组，统计每个城市平均年龄
        Map<String, Double> avgAgeByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity,
                        Collectors.averagingInt(Person::getAge)));// {New York=26.5, London=30.0, Paris=22.0}
        
    }
}

class Person {
    private String name;
    private int age;
    private String city;
    
    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
}
