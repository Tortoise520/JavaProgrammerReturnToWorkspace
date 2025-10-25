import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static String getDateBeforeDays(int days) {
        return LocalDate.now().minusDays(days).format(DEFAULT_FORMATTER);
    }
}
