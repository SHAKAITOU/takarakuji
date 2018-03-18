package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sha.work.dto.loto.Loto7;
import sha.work.dto.loto.Loto7AnalysisBase;
import sha.work.entity.in.Loto7EstimateDataIn;
import sha.work.entity.query.NumberAndTurnsQuery;

@Data
@EqualsAndHashCode(callSuper=false)
public class Loto7EstimateDataOut extends Loto7EstimateDataIn {

	/** 最大数と最小数の差 */
	private int maxMinDiffRank;
	private int maxMinDiffPt;
	/** 総平均値 */
	private int totalAvgRank;
	private int totalAvgPt;
	/** 最大数差 */
	private int maxNumDiffRank;
	private int maxNumDiffPt;
	/** 最小数差 */
	private int minNumDiffRank;
	private int minNumDiffPt;
	/** 数差の平均値 */
	private int numDiffAvgRank;
	private int numDiffAvgPt;
	/** 偶数出る回数 */
	private int evenNumCntRank;
	private int evenNumCntPt;
	/** 奇数出る回数 */
	private int oddNumCntRank;
	private int oddNumCntPt;
	/** 連番出る回数 */
	private int serialNumCntRank;
	private int serialNumCntPt;
	/** 左エリア数出る回数 */
	private int leftAreaNumCntRank;
	private int leftAreaNumCntPt;
	/** 中エリア数出る回数 */
	private int centerAreaNumCntRank;
	private int centerAreaNumCntPt;
	/** 右エリア数出る回数 */
	private int rightAreaNumCntRank;
	private int rightAreaNumCntPt;

	private Loto7AnalysisBase estimateAnalysisBase;
	
	private Loto7AnalysisP2Out loto7AnalysisP2Out;
	
	private Loto7SevenAnalysisOut loto7SevenAnalysisOut;
	
	public void estimateRank() {
		int rank = 1;
		int point = 10;
		for(NumberAndTurnsQuery turn : loto7AnalysisP2Out.getSummaryTotalAvgList()) {
			
			totalAvgRank = ++rank;
			totalAvgPt = --point;
			if(estimateAnalysisBase.getTotalAvg() == Integer.valueOf(turn.getNumberName())) {
				break;
			}
		}
	}
}
