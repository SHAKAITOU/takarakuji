package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import sha.work.entity.query.NumberAndTurnsQuery;

@Data
public class Loto6AnalysisP2Out {
	List<NumberAndTurnsQuery> summaryTotalAvgList;
	
	List<NumberAndTurnsQuery> summaryMaxNumDiffList;
	
	List<NumberAndTurnsQuery> summaryMinNumDiffList;
	
	List<NumberAndTurnsQuery> summaryMaxMinDiffList;
	
	List<NumberAndTurnsQuery> summaryNumDiffAvgList;
	
	List<NumberAndTurnsQuery> summaryEvenNumCntList;
	
	List<NumberAndTurnsQuery> summaryOddNumCntList;
	
	List<NumberAndTurnsQuery> summarySerialNumCntList;
	
	List<NumberAndTurnsQuery> summaryLeftAreaNumCntList;
	
	List<NumberAndTurnsQuery> summaryCenterAreaNumCntList;
	
	List<NumberAndTurnsQuery> summaryRightAreaNumCntList;
}
