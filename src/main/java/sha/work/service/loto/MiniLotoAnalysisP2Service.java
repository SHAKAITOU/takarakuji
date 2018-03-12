package sha.work.service.loto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.entity.out.MiniLotoAnalysisP2Out;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.MiniLotoAnalysisBaseMapper;

@Service
public class MiniLotoAnalysisP2Service extends BaseService {

	/** DB access class. */
	@Autowired
	private MiniLotoAnalysisBaseMapper miniLotoAnalysisBaseMapper;


	public MiniLotoAnalysisP2Out summary() throws TKRKScreenException {
		
		MiniLotoAnalysisP2Out analysisP2Out = new MiniLotoAnalysisP2Out();
		
		analysisP2Out.setSummaryTotalAvgList(miniLotoAnalysisBaseMapper.summaryTotalAvg());
		calculatePercent(analysisP2Out.getSummaryTotalAvgList());
		
		analysisP2Out.setSummaryMaxNumDiffList(miniLotoAnalysisBaseMapper.summaryMaxNumDiff());
		calculatePercent(analysisP2Out.getSummaryMaxNumDiffList());
		
		analysisP2Out.setSummaryMinNumDiffList(miniLotoAnalysisBaseMapper.summaryMinNumDiff());
		calculatePercent(analysisP2Out.getSummaryMinNumDiffList());
		
		analysisP2Out.setSummaryMaxMinDiffList(miniLotoAnalysisBaseMapper.summaryMaxMinDiff());
		calculatePercent(analysisP2Out.getSummaryMaxMinDiffList());
		
		analysisP2Out.setSummaryNumDiffAvgList(miniLotoAnalysisBaseMapper.summaryNumDiffAvg());
		calculatePercent(analysisP2Out.getSummaryNumDiffAvgList());
		
		analysisP2Out.setSummaryEvenNumCntList(miniLotoAnalysisBaseMapper.summaryEvenNumCnt());
		calculatePercent(analysisP2Out.getSummaryEvenNumCntList());
		
		analysisP2Out.setSummaryOddNumCntList(miniLotoAnalysisBaseMapper.summaryOddNumCnt());
		calculatePercent(analysisP2Out.getSummaryOddNumCntList());

		analysisP2Out.setSummarySerialNumCntList(miniLotoAnalysisBaseMapper.summarySerialNumCnt());
		calculatePercent(analysisP2Out.getSummarySerialNumCntList());

		analysisP2Out.setSummaryLeftAreaNumCntList(miniLotoAnalysisBaseMapper.summaryLeftAreaNumCnt());
		calculatePercent(analysisP2Out.getSummaryLeftAreaNumCntList());

		analysisP2Out.setSummaryCenterAreaNumCntList(miniLotoAnalysisBaseMapper.summaryCenterAreaNumCnt());
		calculatePercent(analysisP2Out.getSummaryCenterAreaNumCntList());

		
		analysisP2Out.setSummaryRightAreaNumCntList(miniLotoAnalysisBaseMapper.summaryRightAreaNumCnt());
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
