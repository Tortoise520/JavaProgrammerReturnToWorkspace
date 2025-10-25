import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       List<String> list = Arrays.asList("a", "b", "c", "d", "e");
       //传统方式，匿名内部类
       Collections.sort(list, new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
               return o2.compareTo(o1);
           }
       });
       //lambda表达式
       Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
       //更简洁的写法
        list.sort((o1, o2) -> o1.compareTo(o2));
        //方法引用
        list.sort(String::compareTo);
        System.out.println("排序后：" + list);
        
        List<Person> personList = Arrays.asList(
                new Person("Tom", 12),
                new Person("John", 30),
                new Person("Jerry", 10)
        );
        //sort by age
        personList.sort((o1, o2) -> o1.getAge() - o2.getAge());
        System.out.println("sort by age：" + personList);
        //sort by name
        personList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        System.out.println("sort by name：" + personList);
        //sort by name and age
        personList.sort((p1, p2) -> {
            int age = Integer.compare(p1.getAge(), p2.getAge());
            return age == 0 ? p1.getName().compareTo(p2.getName()) : age;
        });
        System.out.println("sort by name and age：" + personList);
    }
}

class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}