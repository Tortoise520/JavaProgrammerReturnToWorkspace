## Java8新日期时间API详解
Java 8 引入的 `java.time` 包全新日期时间 API，解决了旧 `Date` 和 `Calendar` 类线程不安全、API 设计混乱等问题。新 API 核心思想是将时间划分为**瞬时**、**日期/时间**和**时间段**，设计了清晰且线程安全的类。

下面的表格可以帮助你快速了解这些核心类及其用途。

| **类别** | **核心类** | **描述** | **示例** |
| :--- | :--- | :--- | :--- |
| **日期与时间** | `LocalDate` | 只包含**年月日**，不包含时间信息。 | 生日、纪念日。 |
| | `LocalTime` | 只包含**时分秒**，不包含日期信息。 | 会议开始时间。 |
| | `LocalDateTime` | 包含**年月日**和**时分秒**，但不含时区。 | 订单创建时间。 |
| **瞬时与时区** | `Instant` | 时间轴上的**瞬时点**，用于时间戳记录。 | 日志记录、性能统计。 |
| | `ZonedDateTime` | 包含时区的完整日期时间。 | 跨时区航班时刻。 |
| **时间段** | `Period` | 用于计算两个**日期**之间的间隔（年、月、日）。 | 计算年龄。 |
| | `Duration` | 用于计算两个**时间**之间的间隔（小时、分、秒）。 | 计算操作耗时。 |
| **格式化** | `DateTimeFormatter` | 用于日期时间的**格式化和解析**，线程安全。 | 替换旧的`SimpleDateFormat`。 |

### 🔧 核心操作与示例

掌握了核心类之后，我们来看一些最常见的操作。

#### **1. 获取与创建**
- **获取当前日期时间**：
  ```java
  LocalDate today = LocalDate.now();
  LocalTime now = LocalTime.now();
  LocalDateTime currentDateTime = LocalDateTime.now();
  ```
- **创建特定日期时间**：使用 `of` 方法，参数直观，避免了旧 API 中月份从 0 开始的困惑。
  ```java
  LocalDate birthday = LocalDate.of(1990, 5, 20); // 1990年5月20日
  LocalTime meetingTime = LocalTime.of(14, 30); // 下午2:30
  LocalDateTime appointment = LocalDateTime.of(2025, 10, 25, 9, 15); // 2025年10月25日 9:15
  ```

#### **2. 日期时间计算**
新 API 提供了非常直观的方法进行加减操作，所有方法都返回新的对象，保证了不可变性。
- **增加或减少时间**：
  ```java
  LocalDate nextWeek = today.plusWeeks(1); // 一周后的日期
  LocalDateTime inTwoHours = currentDateTime.plusHours(2); // 两小时后的日期时间
  LocalDate lastMonth = today.minusMonths(1); // 一个月前的日期
  ```
- **使用 `TemporalAdjusters`**：对于更复杂的调整，如"获取本月最后一个周五"，可以使用 `TemporalAdjusters` 类。
  ```java
  LocalDate lastFriday = today.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
  ```

#### **3. 日期时间比较**
比较操作变得简单直接。
```java
LocalDate date1 = LocalDate.of(2025, 10, 25);
LocalDate date2 = LocalDate.of(2024, 10, 25);

boolean isAfter = date1.isAfter(date2); // true
boolean isBefore = date1.isBefore(date2); // false
boolean isEqual = date1.isEqual(date2); // false
```

#### **4. 格式化与解析**
使用 `DateTimeFormatter` 替代线程不安全的 `SimpleDateFormat`。
- **格式化**（日期时间 → 字符串）：
  ```java
  LocalDateTime now = LocalDateTime.now();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  String formattedString = now.format(formatter); // 输出：2025-10-25 14:30:15
  ```
- **解析**（字符串 → 日期时间）：
  ```java
  String dateString = "2025/10/25";
  DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy/MM/dd");
  LocalDate parsedDate = LocalDate.parse(dateString, parser);
  ```

#### **5. 计算时间间隔**
- 使用 `Period` 计算日期间隔：
  ```java
  LocalDate startDate = LocalDate.of(2025, 1, 1);
  LocalDate endDate = LocalDate.of(2025, 10, 25);
  Period period = Period.between(startDate, endDate);
  System.out.println(period.getMonths() + "个月" + period.getDays() + "天"); // 输出：9个月24天
  ```
- 使用 `Duration` 计算时间间隔：
  ```java
  LocalTime startTime = LocalTime.of(10, 0);
  LocalTime endTime = LocalTime.of(12, 30);
  Duration duration = Duration.between(startTime, endTime);
  System.out.println(duration.toMinutes() + "分钟"); // 输出：150分钟
  ```

### 💡 实践建议与常见场景

1.  **新旧 API 转换**：与旧系统交互时，可以通过 `Instant` 作为桥梁与 `java.util.Date` 进行转换。
    ```java
    // Date 转 LocalDateTime
    Date oldDate = new Date();
    LocalDateTime newDateTime = oldDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    
    // LocalDateTime 转 Date
    LocalDateTime localDateTime = LocalDateTime.now();
    Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    ```

2.  **处理周期性事件**：可以使用 `MonthDay`（月日）或 `YearMonth`（年月）类来检查像生日、账单日这样的周期性事件。

3.  **企业级项目封装**：建议将常用的日期时间操作封装成工具类，提升代码的复用性和可维护性。
    ```java
    public class DateUtils {
        private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        public static String getDateBeforeDays(int days) {
            return LocalDate.now().minusDays(days).format(DEFAULT_FORMATTER);
        }
    }
    ```

### 💎 总结

Java 8 的新日期时间 API 通过清晰的设计和不可变性，让日期时间处理变得安全、直观和强大。对于新项目，应完全采用这套新 API；对于旧项目，也建议逐步重构，以提升代码质量。

希望这份指南能帮助你熟练运用现代 Java 的日期时间处理。如果在具体实践中遇到时区处理等更复杂的场景，我很乐意继续为你解答。