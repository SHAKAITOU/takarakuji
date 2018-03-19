package sha.work.util;

import sha.work.enums.Rank;

public class HtmlHelper {

	public static String getRankKey(int rankId) {
		for(Rank rank : Rank.values()) {
			if(rank.getId() == rankId) {
				return rank.getKey();
			}
		}
		
		return Rank.Z.getKey();
	}
	
	public static String getTooltipHtmlForEstimateItem(
			String itemNm, String numberName, String valueNm, String numberValue) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(itemNm).append(":");  
		sb.append("<span class='badge badge-warning'>");
		sb.append(numberName);
		sb.append("</span>");
		sb.append("<br/>");
		sb.append(valueNm);
		sb.append(":<span class='badge badge-primary'>");
		sb.append(numberValue);
		sb.append("</span>");
		
		return sb.toString();
	}
}
