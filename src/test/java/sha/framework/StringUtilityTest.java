package sha.framework;

import static org.assertj.core.api.Assertions.*;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import sha.framework.util.StringUtility;
import sha.framework.util.StringUtility.NumberClass;
import sha.framework.util.StringUtility.RoundMode;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StringUtilityTest {

    /**
     * test parseNumber method.
     * @see StringUtility#parseNumber(String, NumberClass)
     */
    @Test
    public void testParseNumber() {
        assertThat(StringUtility.parseNumber("1", NumberClass.INTEGER)).isEqualTo(1);
        assertThat(StringUtility.parseNumber("A", NumberClass.INTEGER)).isNull();
        assertThat(StringUtility.parseNumber("１", NumberClass.INTEGER)).isEqualTo(1);
        assertThat(StringUtility.parseNumber("あ", NumberClass.INTEGER)).isNull();
        assertThat(StringUtility.parseNumber("あ1", NumberClass.INTEGER)).isNull();
        assertThat(StringUtility.parseNumber("ｱ1", NumberClass.INTEGER)).isNull();
        assertThat(StringUtility.parseNumber("ａ1", NumberClass.INTEGER)).isNull();

        assertThat(StringUtility.parseNumber("1", NumberClass.LONG)).isEqualTo(1L);
        assertThat(StringUtility.parseNumber("A", NumberClass.LONG)).isNull();
        assertThat(StringUtility.parseNumber("１", NumberClass.LONG)).isEqualTo(1L);
        assertThat(StringUtility.parseNumber("あ", NumberClass.LONG)).isNull();
        assertThat(StringUtility.parseNumber("あ1", NumberClass.LONG)).isNull();
        assertThat(StringUtility.parseNumber("ｱ1", NumberClass.LONG)).isNull();
        assertThat(StringUtility.parseNumber("ａ1", NumberClass.LONG)).isNull();

        assertThat(StringUtility.parseNumber("1", NumberClass.FLOAT)).isEqualTo(1.0F);
        assertThat(StringUtility.parseNumber("1.0", NumberClass.FLOAT)).isEqualTo(1.0F);
        assertThat(StringUtility.parseNumber("A", NumberClass.FLOAT)).isNull();
        assertThat(StringUtility.parseNumber("１", NumberClass.FLOAT)).isNull();
        assertThat(StringUtility.parseNumber("１．０", NumberClass.FLOAT)).isNull();
        assertThat(StringUtility.parseNumber("あ", NumberClass.FLOAT)).isNull();
        assertThat(StringUtility.parseNumber("あ1.0", NumberClass.FLOAT)).isNull();
        assertThat(StringUtility.parseNumber("ｱ1.0", NumberClass.FLOAT)).isNull();
        assertThat(StringUtility.parseNumber("ａ1.0", NumberClass.FLOAT)).isNull();

        assertThat(StringUtility.parseNumber("1", NumberClass.DOUBLE)).isEqualTo(1.0D);
        assertThat(StringUtility.parseNumber("1.0", NumberClass.DOUBLE)).isEqualTo(1.0D);
        assertThat(StringUtility.parseNumber("A", NumberClass.DOUBLE)).isNull();
        assertThat(StringUtility.parseNumber("１", NumberClass.DOUBLE)).isNull();
        assertThat(StringUtility.parseNumber("１．０", NumberClass.DOUBLE)).isNull();
        assertThat(StringUtility.parseNumber("あ", NumberClass.DOUBLE)).isNull();
        assertThat(StringUtility.parseNumber("あ1.0", NumberClass.DOUBLE)).isNull();
        assertThat(StringUtility.parseNumber("ｱ1.0", NumberClass.DOUBLE)).isNull();
        assertThat(StringUtility.parseNumber("ａ1.0", NumberClass.DOUBLE)).isNull();
    }

    /**
     * test getByteLength method.
     * @see StringUtility#getByteLength(String, String)
     */
    @Test
    public void testGetByteLengthWithCharset() {
        try {
            assertThat(StringUtility.getByteLength("abcde", "UTF-8")).isEqualTo(5);
            assertThat(StringUtility.getByteLength("あいうえお", "UTF-8")).isEqualTo(15);
            assertThat(StringUtility.getByteLength("あいうえお", "Shift-JIS")).isEqualTo(10);
            assertThat(StringUtility.getByteLength("abcdeあいうえお", "UTF-8")).isEqualTo(20);
            assertThat(StringUtility.getByteLength("abcdeあいうえお", "Shift-JIS")).isEqualTo(15);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * test getByteLength method.
     * @see StringUtility#getByteLength(String)
     */
    @Test
    public void testGetByteLength() {
        try {
            assertThat(StringUtility.getByteLength("abcde")).isEqualTo(5);
            assertThat(StringUtility.getByteLength("あいうえお")).isEqualTo(15);
            assertThat(StringUtility.getByteLength("abcdeあいうえお")).isEqualTo(20);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * test getByteLength method when throw exception.
     * @see StringUtility#getByteLength(String, String)
     * @throws UnsupportedEncodingException when the specified Character Encoding is not supported.
     */
    @Test(expected = UnsupportedEncodingException.class)
    public void testGetByteLengthThrowException() throws UnsupportedEncodingException {
        StringUtility.getByteLength("abcdeあいうえお", "adfg");
    }

    /**
     * test formatNumber method.
     * @see StringUtility#formatNumber(long, Locale)
     */
    @Test
    public void testFormatNumberLong() {
        assertThat(StringUtility.formatNumber(12345L, 
                Locale.FRANCE).equals("12 345")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(12L, 
                Locale.GERMAN).equals("12")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(-12345L, 
                Locale.FRANCE).equals("-12 345")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(-12L, 
                Locale.FRANCE).equals("-12")).isEqualTo(true);
       
    }
    
    /**
     * test formatNumber method.
     * @see StringUtility#formatNumber(double, int, RoundMode, Locale)
     */
    @Test
    public void testFormatNumberDouble() {

        assertThat(StringUtility.formatNumber(12345.567, 2, 
                RoundMode.ROUND, Locale.FRANCE).equals("12 345,57")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(345.55, 1, 
                RoundMode.ROUND, Locale.JAPAN).equals("345.6")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(1.2345, 3, 
                RoundMode.ROUND, Locale.CHINESE).equals("1.235")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(0.2355, 3, 
                RoundMode.ROUND, Locale.CHINESE).equals("0.236")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(12345.563, 2, 
                RoundMode.CEIL, Locale.CHINESE).equals("12,345.57")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(345.53, 1, 
                RoundMode.CEIL, Locale.CHINESE).equals("345.6")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(1.2344, 3, 
                RoundMode.CEIL, Locale.CHINESE).equals("1.235")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(0.2353, 3, 
                RoundMode.CEIL, Locale.CHINESE).equals("0.236")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(12345.567, 2, 
                RoundMode.FLOOR, Locale.CHINESE).equals("12,345.56")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(345.55, 1, 
                RoundMode.FLOOR, Locale.CHINESE).equals("345.5")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(1.2345, 3, 
                RoundMode.FLOOR, Locale.CHINESE).equals("1.234")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(0.2355, 3, 
                RoundMode.FLOOR, Locale.CHINESE).equals("0.235")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(
                1234567.891234, 3, RoundMode.ROUND, 
                Locale.CHINESE).equals("1,234,567.891")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(-22345.5678, 3, 
                RoundMode.ROUND, Locale.CHINESE).equals("-22,345.568")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(-345.55, 1, 
                RoundMode.ROUND, Locale.CHINESE).equals("-345.6")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(-1.2345, 3, 
                RoundMode.ROUND, Locale.CHINESE).equals("-1.235")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(-0.2355, 3, 
                RoundMode.ROUND, Locale.CHINESE).equals("-0.236")).isEqualTo(true);
        assertThat(StringUtility.formatNumber(
                -1234567.891234, 3, RoundMode.ROUND, 
                Locale.CHINESE).equals("-1,234,567.891")).isEqualTo(true);
    }
    
    /**
     * test formatCurrency method.
     * @see StringUtility#formatCurrency(long, Locale)
     */
    @Test
    public void testFormatCurrencyLong() {
        assertThat(StringUtility.formatCurrency(
                12345L, Locale.FRANCE).equals("12 345,00 €")).isEqualTo(true);
        assertThat(StringUtility.formatCurrency(
                12L, Locale.CANADA).equals("$12.00")).isEqualTo(true);
    }
    
    /**
     * test formatCurrency method.
     * @see StringUtility#formatCurrency(double, RoundMode, Locale)
     */
    @Test
    public void testFormatCurrencyDouble() {
        assertThat(StringUtility.formatCurrency(
                12345.567, RoundMode.ROUND, 
                Locale.CANADA).equals("$12,345.57")).isEqualTo(true);
        assertThat(StringUtility.formatCurrency(
                345.55, RoundMode.ROUND, Locale.JAPAN).equals("￥346")).isEqualTo(true);
        assertThat(StringUtility.formatCurrency(
                1.2345, RoundMode.ROUND, Locale.CANADA).equals("$1.23")).isEqualTo(true);
        assertThat(StringUtility.formatCurrency(
                0.2355, RoundMode.ROUND, Locale.FRANCE).equals("0,24 €")).isEqualTo(true);
        assertThat(StringUtility.formatCurrency(
                12345.563, RoundMode.CEIL, Locale.CANADA).equals("$12,345.57")).isEqualTo(true);
        assertThat(StringUtility.formatCurrency(
                345.53, RoundMode.CEIL, Locale.CANADA).equals("$345.53")).isEqualTo(true);
    }
    
    /**
     * test formatPercent method.
     * @see StringUtility#formatPercent(double, int, RoundMode, Locale)
     */
    @Test
    public void testFormatPercent() {
        assertThat(StringUtility.formatPercent(
                12345.567, 2, RoundMode.ROUND, 
                Locale.FRANCE).equals("1 234 557 %")).isEqualTo(true);
        assertThat(StringUtility.formatPercent(
                345.55, 0, RoundMode.ROUND, Locale.JAPAN).equals("34,600%")).isEqualTo(true);
        assertThat(StringUtility.formatPercent(
                1.2345, 3, RoundMode.ROUND, Locale.CHINESE).equals("124%")).isEqualTo(true);
        assertThat(StringUtility.formatPercent(
                0.2355, 1, RoundMode.ROUND, Locale.FRANCE).equals("20 %")).isEqualTo(true);
        assertThat(StringUtility.formatPercent(
                12345.563, 2, RoundMode.CEIL, 
                Locale.CHINESE).equals("1,234,557%")).isEqualTo(true);
        assertThat(StringUtility.formatPercent(
                1.2344, 1, RoundMode.CEIL, Locale.JAPAN).equals("130%")).isEqualTo(true);
        assertThat(StringUtility.formatPercent(
                0.2355, 1, RoundMode.FLOOR, Locale.CHINESE).equals("20%")).isEqualTo(true);
        
    }
   
}
