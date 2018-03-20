package sha.work.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import sha.framework.util.DateUtility;
import sha.work.common.CommonConstants;

public class LotoUtil {


	public static String getNextLoto7OpenDt(String openDt) {
		LocalDate localDate = DateUtility.parseDate(openDt, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
		localDate = localDate.plusDays(CommonConstants.LOTO7_INTERVAL_DAYS);
		return DateUtility.formatDate(localDate, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
	}
	
	public static String getNextLoto6OpenDt(String openDt) {
		LocalDate localDate = DateUtility.parseDate(openDt, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
		DayOfWeek dw = localDate.getDayOfWeek();
		if(dw == DayOfWeek.THURSDAY) {
			localDate = localDate.plusDays(CommonConstants.LOTO6_INTERVAL_DAYS_DOWN);
		} else {
			localDate = localDate.plusDays(CommonConstants.LOTO6_INTERVAL_DAYS_UP);
		}
		return DateUtility.formatDate(localDate, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
	}
	
	public static String getNextMiniLotoOpenDt(String openDt) {
		LocalDate localDate = DateUtility.parseDate(openDt, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
		localDate = localDate.plusDays(CommonConstants.MINILOTO_INTERVAL_DAYS);
		return DateUtility.formatDate(localDate, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
	}
}
