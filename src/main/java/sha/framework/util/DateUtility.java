package sha.framework.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Common component <br>
 * date utility class.
 *
 */
@Slf4j
public class DateUtility {

    private DateUtility() {}
    
    /**
     * Convert the specified text to a LocalDate with the specified format pattern.
     * 
     * @param dateString specified date string.
     * @param pattern  specified format pattern.<BR>
     *      see {@link DateFormat} to use the predefined patterns <BR>
     *      see {@link DateTimeFormatter} to use more supported patterns <BR>
     *      use "uuuu-MM-dd" as default format when empty
     * @return {@link LocalDate} when success, null otherwise
     */
    public static LocalDate parseDate(String dateString, String pattern) {
        
        if (!StringUtils.hasText(dateString)) {
            return null;
        } 
        String formatPattern = pattern;
        if (!StringUtils.hasText(formatPattern)) {
            formatPattern = DateFormat.UUUUHMMHDD.getFormat();
        }
        
        try {
            DateTimeFormatter dateTimeFormatter = 
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDate.parse(dateString, dateTimeFormatter);

        } catch (DateTimeParseException | IllegalArgumentException e) {
            log.error("error in parsing date string.", e);
        }

        return null;
    }
    
    /**
     * Convert the specified text to a LocalDateTime with the specified format pattern.
     * 
     * @param dateTimeString specified dateTime string.
     * @param pattern specified format pattern.<BR>
     *      see {@link DateTimeFormat} to use the predefined pattern. <BR>
     *      see {@link DateTimeFormatter} to use more patterns. <BR>
     *      use "uuuu-MM-dd HH:mm:ss.SSS" as default format when empty
     * @return {@link LocalDateTime} when success, null otherwise
     */
    public static LocalDateTime parseDateTime(String dateTimeString, String pattern) {
        
        if (!StringUtils.hasText(dateTimeString)) {
            return null;
        } 
        
        String formatPattern = pattern;
        if (!StringUtils.hasText(formatPattern)) {
            formatPattern =  DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat();
        }
        
        try {
            DateTimeFormatter dateTimeFormatter = 
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            log.error("error in parsing date string.", e);
        }
        
        return null;
    }
    
    /**
     * Convert the specified localDate to a date string with the specified format pattern.
     * @param localDate the specified localDate.
     * @param pattern the specified format pattern.<BR>
     *      see {@link DateFormat} to use the predefined pattern. <BR>
     *      see {@link DateTimeFormatter} to use more pattern. <BR>
     *      use "uuuu-MM-dd" as default format when empty.
     * @return a formatted date string when success, null otherwise.
     */
    public static String formatDate(LocalDate localDate, String pattern) {
        
        if (localDate == null) {
            return null;
        }
        
        String formatPattern = pattern;
        if (!StringUtils.hasText(formatPattern)) {
            formatPattern =  DateFormat.UUUUHMMHDD.getFormat();
        }
        
        try {
            DateTimeFormatter dateTimeFormatter = 
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return localDate.format(dateTimeFormatter);
        } catch (DateTimeException | IllegalArgumentException e) {
            log.error("error in formatting date with pattern.", e);
        }
        
        return null;
    }
    
    /**
     * Convert the specified localDateTime to a date time text with the specified format pattern.
     * @param localDateTime the specified localDateTime.
     * @param pattern the specified format pattern. <BR>
     *      see {@link DateTimeFormat} to use the predefined pattern. <BR>
     *      see {@link DateTimeFormatter} to use more pattern. <BR>
     *      use "uuuu-MM-dd HH:mm:ss.SSS" as default format when empty.
     * @return a date time text when success, null otherwise.
     */
    public static String formatDateTime(LocalDateTime localDateTime, String pattern) {
        
        if (localDateTime == null) {
            return null;
        }

        String formatPattern = pattern;
        if (!StringUtils.hasText(formatPattern)) {
            formatPattern =  DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat();
        }

        try {
            DateTimeFormatter dateTimeFormatter = 
                    DateTimeFormatter.ofPattern(formatPattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return localDateTime.format(dateTimeFormatter);
        } catch (DateTimeException | IllegalArgumentException e) {
            log.error("error in formatting date with pattern.", e);
        }
        
        return null;
    }

    /**
     * convert a localDateTime with the specified time zone from. 
     * to a localDateTime with the specified time zone to.
     * @param localDateTime a specified localDateTime from.
     * @param fromTimeZone a specified time zone from.
     * @param toTimeZone a specified time zone to.
     * @return {@link LocalDateTime} when success, null otherwise.
     */
    public static LocalDateTime convertTimeZone(LocalDateTime localDateTime,
            TimeZone fromTimeZone, TimeZone toTimeZone) {
        
        if (localDateTime == null || fromTimeZone == null || toTimeZone == null) {
            return null;
        }
        
        try {
            ZonedDateTime orginZonedDateTime = localDateTime.atZone(fromTimeZone.toZoneId());
            return orginZonedDateTime.toInstant().atZone(toTimeZone.toZoneId())
                    .toLocalDateTime();
        } catch (DateTimeException e) {
            log.error("error in converting time zone.", e);
        }
        
        return null;
    }
    
    /**
     * Calculate the amount of days between the specified two LocalDate.
     * @param stringFromDate from LocalDate
     * @param stringToDate to LocalDate
     * @param pattern the specified format pattern<BR>
     *      see {@link DateFormat} to use the predefined pattern <BR>
     *      see {@link DateTimeFormatter} to use the more pattern <BR>
     *      use "uuuu-MM-dd" as default format when empty
     * @return the amount of days between two LocalDate, null when failed
     */
    public static Long getDateDifference(String stringFromDate, 
            String stringToDate, String pattern) {
       
        LocalDate fromDate = parseDate(stringFromDate, pattern);
        LocalDate toDate = parseDate(stringToDate, pattern);
        
        if (fromDate == null || toDate == null) {
            return null;
        }
        
        return ChronoUnit.DAYS.between(fromDate, toDate);
    }
     
    /**
     * Calculate the amount of time between the specified two LocalDateTimes.
     * 
     * @param stringFromDateTime from LocalDateTime
     * @param stringToDateTime to LocalDateTime
     * @param pattern the specified format pattern<BR>
     *      see {@link DateTimeFormatType} to use the predefined pattern <BR>
     *      see {@link DateTimeFormatter} to use more pattern <BR>
     *      use {@link DateTimeFormatType.UUUUHMMHDD} as default format when empty
     * @return the amount of time between two LocalDateTime, null when failed
     */
    public static Long getDateTimeDifference(
            String stringFromDateTime, String stringToDateTime, String pattern) {
        
        LocalDateTime fromDateTime = parseDateTime(stringFromDateTime, pattern);
        LocalDateTime toDateTime = parseDateTime(stringToDateTime, pattern);

        if (fromDateTime == null || toDateTime == null) {
            return null;
        }
        
        return ChronoUnit.MILLIS.between(fromDateTime, toDateTime);
    }
    
    /**
     * default date format pattern enum.
     *
     */
    public enum DateFormat {
        /** date format context<br> (uuuuMMdd). */
        UUUUMMDD("uuuuMMdd"), 
        /** date format context<br> (uuuu/MM/dd). */
        UUUUSMMSDD("uuuu/MM/dd"),
        /** date format context<br> (uuuu-MM-dd). */
        UUUUHMMHDD("uuuu-MM-dd"),
        /** date format context<br> (uuuu.MM.dd). */
        UUUUDMMDDD("uuuu.MM.dd");
        
        /** format pattern. */
        @Getter
        private String format;

        private DateFormat(String format) {
            this.format = format;
        }
    }

    /**
     * default dateTime format pattern enum.
     *
     */
    public enum DateTimeFormat {
        /** date time format context<br> (uuuu-MM-ddTHH:mm:ss). */
        UUUUHMMHDDTHHQMIQSS("uuuu-MM-dd'T'HH:mm:ss"),
        /** date time format context<br> (uuuu/MM/dd HH:mm:ss). */
        UUUUSMMSDDHHQMIQSS("uuuu/MM/dd HH:mm:ss"),
        /** date time format context<br> (uuuu/MM/ddTHH:mm:ss). */
        UUUUSMMSDDTHHQMIQSS("uuuu/MM/dd'T'HH:mm:ss"),
        /** date time format context<br> (uuuu-MM-dd HH:mm:ss). */
        UUUUHMMHDDHHQMIQSS("uuuu-MM-dd HH:mm:ss"),
        /** date time format context<br> (uuuu-MM-ddTHH:mm:ss). */
        UUUUHMMHDDTHHQMIQSS_SSS("uuuu-MM-dd'T'HH:mm:ss.SSS"),
        /** date time format context<br> (uuuu/MM/dd HH:mm:ss.SSS). */
        UUUUSMMSDDHHQMIQSS_SSS("uuuu/MM/dd HH:mm:ss.SSS"),
        /** date time format context<br> (uuuu/MM/ddTHH:mm:ss.SSS). */
        UUUUSMMSDDTHHQMIQSS_SSS("uuuu/MM/dd'T'HH:mm:ss.SSS"),
        /** date time format context<br> (uuuu-MM-dd HH:mm:ss.SSS). */
        UUUUHMMHDDHHQMIQSS_SSS("uuuu-MM-dd HH:mm:ss.SSS");
        
        /** format pattern. */
        @Getter
        private String format;

        private DateTimeFormat(String format) {
            this.format = format;
        }
    }
}
