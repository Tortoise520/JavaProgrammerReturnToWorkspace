/**
 * 自定义函数式接口
 */
public class ValidatorExample {
    public static void main(String[] args) {
        Validator<String> notNull = Validator.notNull();
        Validator<String> notEmpty = value -> !value.isEmpty();
        Validator<String> lengthValidator = value -> value.length() >= 5;
        
        // 组合验证器
        Validator<String> combined = notNull
                .and(notEmpty)
                .and(lengthValidator);
        
        System.out.println(combined.validate("Hello"));  // true
        System.out.println(combined.validate("Hi"));     // false
    }
}

@FunctionalInterface
interface Validator<T> {
    boolean validate(T value);
    
    default Validator<T> and(Validator<T> other) {
        return value -> this.validate(value) && other.validate(value);
    }
    
    default Validator<T> or(Validator<T> other) {
        return value -> this.validate(value) || other.validate(value);
    }
    
    static <T> Validator<T> notNull() {
        return value -> value != null;
    }
}