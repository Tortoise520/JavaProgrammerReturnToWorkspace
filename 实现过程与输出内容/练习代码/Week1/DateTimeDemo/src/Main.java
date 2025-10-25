import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        getCurrentDateTime();
        createDateTime();
        compareDateTime();
        formatDateTime();
        periodAndDurationCalc();
    }
    
    /**
     * 获取当前日期时间
     */
    static void getCurrentDateTime() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime currDateTime = LocalDateTime.now();
        System.out.println("today:" + today);
        System.out.println("now:" + now);
        System.out.println("currDateTime:" + currDateTime);
        //获取下周日期时间
        LocalDate nextWeek = today.plusWeeks(1);
        LocalTime nextHour = now.plusHours(1);
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate lastFriday = today.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        System.out.println("nextWeek:" + nextWeek);
        System.out.println("nextHour:" + nextHour);
        System.out.println("lastMonth:" + lastMonth);
        System.out.println("lastFriday:" + lastFriday);
    }
    
    /**
     * 使用of方法创建特定日期时间
     */
    static void createDateTime() {
        LocalDate ld = LocalDate.of(2025, 10, 25);
        LocalTime lt = LocalTime.of(10, 16, 37);
        LocalDateTime ldt = LocalDateTime.of(2025, 10, 26, 11, 30, 33);
        System.out.println(ld);
        System.out.println(lt);
        System.out.println(ldt);
    }
    /**
     * 时间比较
     */
    static void compareDateTime() {
        LocalDate date1 = LocalDate.of(2025,4, 20);
        LocalDate date2 = LocalDate.of(2025,5, 20);
        System.out.println(date1.isBefore(date2));
        System.out.println(date1.isAfter(date2));
        System.out.println(date1.isEqual(date2));
        
    }
    /**
     * 日期时间格式化与解析
     */
    static void formatDateTime() {
        //日期 -> 字符串
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowStr = now.format(formatter);
        System.out.println("nowStr:" + nowStr);
        //字符串 -> 日期
        String dateStr = "2025/10/10 10:10:10";
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr,parser);
        System.out.println("date time:" + localDateTime);
    }
    
    /**
     * 计算日期和时间间隔
     */
    static void periodAndDurationCalc() {
        //Period
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 10, 25);
        Period period = Period.between(startDate, endDate);
        System.out.println(period.getMonths() + "个月" + period.getDays() + "天"); // 输出：9个月24天
    
        //Duration
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 30);
        Duration duration = Duration.between(startTime, endTime);
        System.out.println(duration.toMinutes() + "分钟"); // 输出：150分钟
    
    }
    /**
     * 新旧日期时间API相互转换
     */
    static void convert() {
        // Date 转 LocalDateTime
        Date oldDate = new Date();
        LocalDateTime newDateTime = oldDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    // LocalDateTime 转 Date
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    
    }
    
}