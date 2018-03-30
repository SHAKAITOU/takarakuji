package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sha.work.dto.loto.MiniLotoAnalysisBase;
import sha.work.entity.in.MiniLotoEstimateDataIn;
import sha.work.entity.query.NumberAndTurnsQuery;

@Data
@EqualsAndHashCode(callSuper=false)
public class MiniLotoEstimateDataOut extends MiniLotoEstimateDataIn {

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
	
	private int l1Rank;
	private int l1Pt;
	private List<NumberAndTurnsQuery> l1List;
	
	private int l2Rank;
	private int l2Pt;
	private List<NumberAndTurnsQuery> l2List;
	
	private int l3Rank;
	private int l3Pt;
	private List<NumberAndTurnsQuery> l3List;
	
	
	private int l4Rank;
	private int l4Pt;
	private List<NumberAndTurnsQuery> l4List;
	
	
	private int l5Rank;
	private int l5Pt;
	private List<NumberAndTurnsQuery> l5List;
	
	
	private int totalPt = 0;

	private MiniLotoAnalysisBase estimateAnalysisBase;
	
	private MiniLotoAnalysisP2Out miniLotoAnalysisP2Out;
	
	private MiniLotoFiveAnalysisOut miniLotoFiveAnalysisOut;
	
}
