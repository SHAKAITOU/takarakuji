package sha.work.service.loto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.entity.out.Loto6AnalysisP2Out;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto6AnalysisBaseMapper;

@Service
public class Loto6AnalysisP2Service extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto6AnalysisBaseMapper loto6AnalysisBaseMapper;


	public Loto6AnalysisP2Out summary() throws TKRKScreenException {
		
		Loto6AnalysisP2Out analysisP2Out = new Loto6AnalysisP2Out();
		
		analysisP2Out.setSummaryTotalAvgList(loto6AnalysisBaseMapper.summaryTotalAvg());
		calculatePercent(analysisP2Out.getSummaryTotalAvgList());
		
		analysisP2Out.setSummaryMaxNumDiffList(loto6AnalysisBaseMapper.summaryMaxNumDiff());
		calculatePercent(analysisP2Out.getSummaryMaxNumDiffList());
		
		analysisP2Out.setSummaryMinNumDiffList(loto6AnalysisBaseMapper.summaryMinNumDiff());
		calculatePercent(analysisP2Out.getSummaryMinNumDiffList());
		
		analysisP2Out.setSummaryMaxMinDiffList(loto6AnalysisBaseMapper.summaryMaxMinDiff());
		calculatePercent(analysisP2Out.getSummaryMaxMinDiffList());
		
		analysisP2Out.setSummaryNumDiffAvgList(loto6AnalysisBaseMapper.summaryNumDiffAvg());
		calculatePercent(analysisP2Out.getSummaryNumDiffAvgList());
		
		analysisP2Out.setSummaryEvenNumCntList(loto6AnalysisBaseMapper.summaryEvenNumCnt());
		calculatePercent(analysisP2Out.getSummaryEvenNumCntList());
		
		analysisP2Out.setSummaryOddNumCntList(loto6AnalysisBaseMapper.summaryOddNumCnt());
		calculatePercent(analysisP2Out.getSummaryOddNumCntList());

		analysisP2Out.setSummarySerialNumCntList(loto6AnalysisBaseMapper.summarySerialNumCnt());
		calculatePercent(analysisP2Out.getSummarySerialNumCntList());

		analysisP2Out.setSummaryLeftAreaNumCntList(loto6AnalysisBaseMapper.summaryLeftAreaNumCnt());
		calculatePercent(analysisP2Out.getSummaryLeftAreaNumCntList());

		analysisP2Out.setSummaryCenterAreaNumCntList(loto6AnalysisBaseMapper.summaryCenterAreaNumCnt());
		calculatePercent(analysisP2Out.getSummaryCenterAreaNumCntList());

		
		analysisP2Out.setSummaryRightAreaNumCntList(loto6AnalysisBaseMapper.summaryRightAreaNumCnt());
		calculatePercent(analysisP2Out.getSummaryRightAreaNumCntList());

		
		return analysisP2Out;
	}
	
	private void calculatePercent(List<NumberAndTurnsQuery> summaryList) {
		int total = 0;
		for(NumberAndTurnsQuery el : summaryList) {
			total += el.getNumberValue();
		}
		
		for(NumberAndTurnsQuery el : summaryList) {
			double up = el.getNumberValue()+0d;
			el.setPercent(Double.toString(Math.floor(up/total*10000) / 100));
		}
	}
}
