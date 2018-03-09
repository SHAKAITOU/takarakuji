package sha.work.dto.loto;

import lombok.Data;

@Data
public class MiniLotoAnalysisBase {

	/** 回 */
	private int turn;
	/** 最大数 */
	private int maxNum;
	/** 最小数 */
	private int minNum;
	/** 最大数と最小数の差 */
	private int maxMinDiff;
	/** 総平均値 */
	private int totalAvg;
	/** 最大数差 */
	private int maxNumDiff;
	/** 最小数差 */
	private int minNumDiff;
	/** 数差の平均値 */
	private int numDiffAvg;
	/** 偶数出る回数 */
	private int evenNumCnt;
	/** 奇数出る回数 */
	private int oddNumCnt;
	/** 連番出る回数 */
	private int serialNumCnt;
	/** 左エリア数出る回数 */
	private int leftAreaNumCnt;
	/** 中エリア数出る回数 */
	private int centerAreaNumCnt;
	/** 右エリア数出る回数 */
	private int rightAreaNumCnt;
	
}
