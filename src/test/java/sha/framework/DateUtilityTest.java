package sha.framework;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import sha.framework.util.DateUtility;
import sha.framework.util.DateUtility.DateFormat;
import sha.framework.util.DateUtility.DateTimeFormat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DateUtilityTest {

    /**
     * test parseDate method.
     * @see DateUtility#parseDate(String, String)
     */
    @Test
    public void testParseDate() {
        //compare LocalDate object 
        LocalDate orginLocalDate = LocalDate.of(2017, 11, 22);
        
        //pattern uuuuMMdd(20171122) --> LocalDate
        LocalDate targetLocalDate = 
                DateUtility.parseDate("20171122", DateFormat.UUUUMMDD.getFormat());
        assertEquals(orginLocalDate.compareTo(targetLocalDate), 0);
   
        //pattern uuuu-MM-dd(2017-11-22) --> LocalDate
        targetLocalDate = 
                DateUtility.parseDate("2017-11-22", DateFormat.UUUUHMMHDD.getFormat());
        assertEquals(orginLocalDate.compareTo(targetLocalDate), 0);
        
        //pattern uuuu/MM/dd(2017/11/22) --> LocalDate
        targetLocalDate = 
                DateUtility.parseDate("2017/11/22", DateFormat.UUUUSMMSDD.getFormat());
        assertEquals(orginLocalDate.compareTo(targetLocalDate), 0);
        
        //pattern uuuu.MM.dd(2017.11.22) --> LocalDate
        targetLocalDate = 
                DateUtility.parseDate("2017.11.22", DateFormat.UUUUDMMDDD.getFormat());
        
        assertEquals(orginLocalDate.compareTo(targetLocalDate), 0);
      
        //pattern ddMMuuuu(22112017) --> LocalDate
        targetLocalDate = 
                DateUtility.parseDate("22112017", "ddMMuuuu");
        assertEquals(orginLocalDate.compareTo(targetLocalDate), 0);
    }
    
    /**
     * test parseDate method when use wrong format pattern.
     * @see DateUtility#parseDate(String, String)
     */
    @Test
    public void testParseDateWrongFormat() {
        //wrong format pattern dddMMuuuu --> null
        LocalDate targetLocalDate = 
                DateUtility.parseDate("22112017", "dddMMuuuu");
        assertThat(targetLocalDate).isNull();
    }
    
    /**
     * test parseDate method when use wrong date string.
     * @see DateUtility#parseDate(String, String)
     */
    @Test
    public void testParseDateWrongDateString() {
        
        //month not exist(20172211) --> null
        LocalDate targetLocalDate = 
                DateUtility.parseDate("20172211", "uuuuMMdd");
        assertThat(targetLocalDate).isNull();
      
        //day not exist(20170332) --> null
        targetLocalDate = 
                DateUtility.parseDate("20170332", "uuuuMMdd");
        assertThat(targetLocalDate).isNull();
        
        //wrong leap year, day not exist(20170229) --> null
        targetLocalDate = 
                DateUtility.parseDate("20170229", "uuuuMMdd");
        assertThat(targetLocalDate).isNull();
        
        //wrong date(2017A029) --> null
        targetLocalDate = 
                DateUtility.parseDate("2017A029", "uuuuMMdd");
        assertThat(targetLocalDate).isNull();
    }

    /**
     * test parseDateTime method.
     * @see DateUtility#parseDateTime(String, String)
     */
    @Test
    public void testParseDateTime() {
        //compare LocalDateTime object 
        LocalDateTime orginLocalDateTime = LocalDateTime.of(2017, 11, 22, 12, 20, 30);
       
        //pattern uuuu-MM-dd HH:mm:ss(2017-11-22 12:20:30) --> LocalDateTime
        LocalDateTime targetLocalDateTime = 
                DateUtility.parseDateTime("2017-11-22 12:20:30", 
                        DateTimeFormat.UUUUHMMHDDHHQMIQSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
        
        //pattern uuuu/MM/dd HH:mm:ss(2017/11/22 12:20:30) --> LocalDateTime
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/11/22 12:20:30", 
                        DateTimeFormat.UUUUSMMSDDHHQMIQSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
        
        //pattern uuuu-MM-ddTHH:mm:ss(2017-11-22T12:20:30) --> LocalDateTime
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017-11-22T12:20:30", 
                        DateTimeFormat.UUUUHMMHDDTHHQMIQSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
        
        //pattern uuuu/MM/ddTHH:mm:ss(2017/11/22T12:20:30) --> LocalDateTime
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/11/22T12:20:30", 
                        DateTimeFormat.UUUUSMMSDDTHHQMIQSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
        
        
        orginLocalDateTime = LocalDateTime.of(2017, 11, 22, 12, 20, 30, 567000000);
        
        //pattern uuuu-MM-dd HH:mm:ss.SSS(2017-11-22 12:20:30.567) --> LocalDateTime
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017-11-22 12:20:30.567", 
                        DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
        
        //pattern uuuu/MM/dd HH:mm:ss.SSS(2017/11/22 12:20:30.567) --> LocalDateTime
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/11/22 12:20:30.567", 
                        DateTimeFormat.UUUUSMMSDDHHQMIQSS_SSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
    
        //pattern uuuu-MM-ddTHH:mm:ss(2017-11-22T12:20:30.567) --> LocalDateTime
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017-11-22T12:20:30.567", 
                        DateTimeFormat.UUUUHMMHDDTHHQMIQSS_SSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
        
        //pattern uuuu/MM/ddTHH:mm:ss.SSS(2017/11/22T12:20:30.567) --> LocalDateTime
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/11/22T12:20:30.567", 
                        DateTimeFormat.UUUUSMMSDDTHHQMIQSS_SSS.getFormat());
        assertEquals(orginLocalDateTime.compareTo(targetLocalDateTime), 0);
    }
    
    /**
     * test parseDateTime method when use wrong format pattern.
     * @see DateUtility#parseDateTime(String, String)
     */
    @Test
    public void testParseDateTimeWrongFormat() {
      
        //wrong format pattern (uuuu/MM/dd'ss'HH:mm:ss.SSS) --> null
        LocalDateTime targetLocalDateTime = 
                DateUtility.parseDateTime("2017/11/22T12:20:30.567", "uuuu/MM/dd'ss'HH:mm:ss.SSS");
        assertThat(targetLocalDateTime).isNull();
    }
    
    /**
     * test parseDateTime method when use wrong date string.
     * @see DateUtility#parseDateTime(String, String)
     */
    @Test
    public void testParseDateTimeWrongDateString() {
        
        //wrong minutes (2017/11/22T12:70:30.567) --> null
        LocalDateTime targetLocalDateTime = 
                DateUtility.parseDateTime("2017/11/22T12:70:30.567", "uuuu/MM/dd'T'HH:mm:ss.SSS");
        assertThat(targetLocalDateTime).isNull();
        
        //day not exist (2017/01/32T12:20:30.567) --> null
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/01/32T12:20:30.567", "uuuu/MM/dd'T'HH:mm:ss.SSS");
        assertThat(targetLocalDateTime).isNull();
        
        //wrong leap year, day not exist (2017/02/29T12:20:30.567) --> null
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/02/29T12:20:30.567", "uuuu/MM/dd'T'HH:mm:ss.SSS");
        assertThat(targetLocalDateTime).isNull();
        
        //month not exist (2017/22/20T12:20:30.567) --> null
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/22/20T12:20:30.567", "uuuu/MM/dd'T'HH:mm:ss.SSS");
        assertThat(targetLocalDateTime).isNull();
        
        //wrong date (2017/22/A3T12:20:30.567) --> null
        targetLocalDateTime = 
                DateUtility.parseDateTime("2017/22/A3T12:20:30.567", "uuuu/MM/dd'T'HH:mm:ss.SSS");
        assertThat(targetLocalDateTime).isNull();
    }
 
    /**
     * test formatDate method.
     * @see DateUtility#formatDate(LocalDate, String)
     */
    @Test
    public void testFormatDate() {
        //compare LocalDate object 
        LocalDate orginLocalDate = LocalDate.of(2017, 11, 22);
        
        //LocalDate --> 20171122
        String orginDateString = "20171122";
        String targetDateString = 
                DateUtility.formatDate(orginLocalDate, DateFormat.UUUUMMDD.getFormat());
        assertEquals(orginDateString.equals(targetDateString), true);
        
        //LocalDate --> 2017-11-22
        orginDateString = "2017-11-22";
        targetDateString = 
                DateUtility.formatDate(orginLocalDate, DateFormat.UUUUHMMHDD.getFormat());
        assertEquals(orginDateString.equals(targetDateString), true);
        
        //LocalDate --> 2017/11/22
        orginDateString = "2017/11/22";
        targetDateString = 
                DateUtility.formatDate(orginLocalDate, DateFormat.UUUUSMMSDD.getFormat());
        assertEquals(orginDateString.equals(targetDateString), true);
      
        //LocalDate --> 2017.11.22
        orginDateString = "2017.11.22";
        targetDateString = 
                DateUtility.formatDate(orginLocalDate, DateFormat.UUUUDMMDDD.getFormat());
        assertEquals(orginDateString.equals(targetDateString), true);
   
    }
    
    /**
     * test formatDate method when use wrong format pattern.
     * @see DateUtility#formatDate(LocalDate, String)
     */
    @Test
    public void testFormatDateWrongFormat() {
        LocalDate orginLocalDate = LocalDate.of(2017, 11, 22);
        
        //LocalDate --> null
        String targetDateString = 
                DateUtility.formatDate(orginLocalDate, "uuuuMMddd");
        assertThat(targetDateString).isNull();
   
    }

    /**
     * test formatDateTime method.
     * @see DateUtility#formatDateTime(LocalDateTime, String)
     */
    @Test
    public void testFormatDateTime() {
        
        LocalDateTime orginLocalDateTime = LocalDateTime.of(2017, 11, 22, 12, 20, 30);

        //LocalDateTime --> 2017-11-22 12:20:30
        String orginDateTimeString = "2017-11-22 12:20:30";
        String targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUHMMHDDHHQMIQSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);

        //LocalDateTime --> 2017/11/22 12:20:30
        orginDateTimeString = "2017/11/22 12:20:30";
        targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUSMMSDDHHQMIQSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);
        
        //LocalDateTime --> 2017-11-22T12:20:30
        orginDateTimeString = "2017-11-22T12:20:30";
        targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUHMMHDDTHHQMIQSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);
        
        //LocalDateTime --> 2017/11/22T12:20:30
        orginDateTimeString = "2017/11/22T12:20:30";
        targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUSMMSDDTHHQMIQSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);
        
        orginLocalDateTime = LocalDateTime.of(2017, 11, 22, 12, 20, 30, 567000000);
        
        //LocalDateTime --> 2017-11-22 12:20:30.567 
        orginDateTimeString = "2017-11-22 12:20:30.567";
        targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);
       
        //LocalDateTime --> 2017/11/22 12:20:30.567
        orginDateTimeString = "2017/11/22 12:20:30.567";
        targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUSMMSDDHHQMIQSS_SSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);
      
        //LocalDateTime --> 2017-11-22T12:20:30.567
        orginDateTimeString = "2017-11-22T12:20:30.567";
        targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUHMMHDDTHHQMIQSS_SSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);
       
        //LocalDateTime --> 2017/11/22T12:20:30.567
        orginDateTimeString = "2017/11/22T12:20:30.567";
        targetDateTimeString = 
                DateUtility.formatDateTime(orginLocalDateTime, 
                        DateTimeFormat.UUUUSMMSDDTHHQMIQSS_SSS.getFormat());
        assertEquals(orginDateTimeString.equals(targetDateTimeString), true);
    }
    
    /**
     * test formatDateTime method when use wrong format pattern.
     * @see DateUtility#formatDateTime(LocalDateTime, String) 
     */
    @Test
    public void testFormatDateTimeWrongFormat() {
        LocalDateTime orginLocalDateTime = 
                LocalDateTime.of(2017, 11, 22, 12, 20, 30, 567000000);
        
        //LocalDate --> null
        String targetDateString = 
                DateUtility.formatDateTime(orginLocalDateTime, "uuuu/MM/dddfHH:mm:ss.SSS");
        assertThat(targetDateString).isNull();
   
    }
    
    /**
     * test convertTimeZone method.
     * @see DateUtility#convertTimeZone(LocalDateTime, TimeZone, TimeZone)
     */
    @Test
    public void testConvertTimeZone() {
        TimeZone tokyoZone = TimeZone.getTimeZone("Asia/Tokyo");
        LocalDateTime tokyoLocalDateTime = LocalDateTime.of(2017, 11, 22, 14, 20, 30);
        
        TimeZone shanghaiZone = TimeZone.getTimeZone("Asia/Shanghai");
        LocalDateTime shanghaiLocalDateTime = LocalDateTime.of(2017, 11, 22, 13, 20, 30);
       
        LocalDateTime targetLocalDateTime = 
                DateUtility.convertTimeZone(tokyoLocalDateTime, tokyoZone, shanghaiZone);
        
        //1 hour Difference between Tokyo time and Shanghai time
        assertThat(shanghaiLocalDateTime.compareTo(targetLocalDateTime)).isEqualTo(0);
    }
    
    /**
     * test getDateDifference method.
     * @see DateUtility#getDateDifference(String, String, String)
     */
    @Test
    public void testGetDateDifference() {
        //get 3 between 2017-11-22 and 2017-11-25 
        String stringFromDate = "2017-11-22";
        String stringToDate = "2017-11-25";
        Long dateDifference = 
                DateUtility.getDateDifference(stringFromDate, stringToDate, 
                        DateFormat.UUUUHMMHDD.getFormat());
        assertEquals(dateDifference.longValue(), 3);
        
        //get -2 between 2017-11-22 and 2017-11-25 
        stringFromDate = "2017-11-22";
        stringToDate = "2017-11-20";
        dateDifference = 
                DateUtility.getDateDifference(stringFromDate, stringToDate, 
                        DateFormat.UUUUHMMHDD.getFormat());
        assertEquals(dateDifference.longValue(), -2);
        
        //get 0 between 2017-11-22 and 2017-11-22 
        stringFromDate = "2017-11-22";
        stringToDate = "2017-11-22";
        dateDifference = 
                DateUtility.getDateDifference(stringFromDate, stringToDate, 
                        DateFormat.UUUUHMMHDD.getFormat());
        assertEquals(dateDifference.longValue(), 0);
    }
    
    /**
     * test getDateDifference method when use wrong date string.
     * @see DateUtility#getDateDifference(String, String, String)
     */
    @Test
    public void testGetDateDifferenceWrongDate() {
        //get null
        String stringFromDate = "2017-11-A0";
        String stringToDate = "2017-11-25";
        Long dateDifference = 
                DateUtility.getDateDifference(stringFromDate, stringToDate, 
                        DateFormat.UUUUHMMHDD.getFormat());
        assertThat(dateDifference).isNull();
        //get null
        stringFromDate = "2017-11-22";
        stringToDate = "2017-A0-25";
        dateDifference = 
                DateUtility.getDateDifference(stringFromDate, stringToDate, 
                        DateFormat.UUUUHMMHDD.getFormat());
        assertThat(dateDifference).isNull();
        
    }
    
    /**
     * test getDateDifference method when use wrong format pattern.
     * @see DateUtility#getDateDifference(String, String, String) 
     */
    @Test
    public void testGetDateDifferenceWrongFormat() {
        //get null
        String stringFromDate = "2017-11-22";
        String stringToDate = "2017-11-25";
        Long dateDifference = 
                DateUtility.getDateDifference(stringFromDate, stringToDate, 
                        "uuuuMMddd");
        assertThat(dateDifference).isNull();
    }
    
    /**
     * test getDateTimeDifference method.
     * @see DateUtility#getDateTimeDifference(String, String, String)
     */
    @Test
    public void testGetDateTimeDifference() {
        //get 10000 between 2017-11-22 12:00:00 and 2017-11-22 12:00:10
        String stringFromDate = "2017-11-22 12:00:00";
        String stringToDate = "2017-11-22 12:00:10";
        Long dateDifference = 
                DateUtility.getDateTimeDifference(stringFromDate, stringToDate, 
                        DateTimeFormat.UUUUHMMHDDHHQMIQSS.getFormat());
        assertEquals(dateDifference.longValue(), 10000);
        
        //get -10000 between 2017-11-22T12:00:10 and 2017-11-22T12:00:00
        stringFromDate = "2017-11-22T12:00:10";
        stringToDate = "2017-11-22T12:00:00";
        dateDifference = 
                DateUtility.getDateTimeDifference(stringFromDate, stringToDate, 
                        DateTimeFormat.UUUUHMMHDDTHHQMIQSS.getFormat());
        assertEquals(dateDifference.longValue(), -10000);
        
        //get 0 between 2017-11-22 12:00:10.567 and 2017-11-22 12:00:10.567
        stringFromDate = "2017-11-22 12:00:10.567";
        stringToDate = "2017-11-22 12:00:10.567";
        dateDifference = 
                DateUtility.getDateTimeDifference(stringFromDate, stringToDate, 
                        DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat());
        assertEquals(dateDifference.longValue(), 0);
    }
    
    /**
     * test getDateTimeDifference method when use wrong date.
     * @see DateUtility#getDateTimeDifference(String, String, String)
     */
    @Test
    public void testGetDateTimeDifferenceWrongDate() {
        //get null
        String stringFromDate = "2017-11-A0 12:00:10.567";
        String stringToDate = "2017-11-25 12:00:10.567";
        Long dateDifference = 
                DateUtility.getDateTimeDifference(stringFromDate, stringToDate, 
                        DateTimeFormat.UUUUHMMHDDHHQMIQSS_SSS.getFormat());
        assertThat(dateDifference).isNull();
        //get null
        stringFromDate = "2017-11-22T12:00:10.567";
        stringToDate = "2017-A0-25T12:00:10.567";
        dateDifference = 
                DateUtility.getDateTimeDifference(stringFromDate, stringToDate, 
                        DateTimeFormat.UUUUHMMHDDTHHQMIQSS_SSS.getFormat());
        assertThat(dateDifference).isNull();
        
    }
    
    /**
     * test getDateTimeDifference method when use wrong format pattern.
     * @see DateUtility#getDateTimeDifference(String, String, String)
     */
    @Test
    public void testGetDateTimeDifferenceWrongFormat() {
        //get null
        String stringFromDate = "2017-11-22T12:00:10.567";
        String stringToDate = "2017-11-25T12:00:10.567";
        Long dateDifference = 
                DateUtility.getDateTimeDifference(stringFromDate, stringToDate, 
                        "uuuuMMddd HH:mm:ss.SSS");
        assertThat(dateDifference).isNull();
    }
    
}
