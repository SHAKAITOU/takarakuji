package sha.work.util;

import java.time.LocalDate;

import sha.framework.util.DateUtility;
import sha.work.common.CommonConstants;

public class LotoUtil {


	public static String getNextLoto7OpenDt(String openDt) {
		LocalDate localDate = DateUtility.parseDate(openDt, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
		localDate = localDate.plusDays(CommonConstants.LOTO7_INTERVAL_DAYS);
		return DateUtility.formatDate(localDate, DateUtility.DateFormat.UUUUSMMSDD.getFormat());
	}
}
