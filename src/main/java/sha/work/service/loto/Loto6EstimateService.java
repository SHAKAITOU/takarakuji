package sha.work.service.loto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import sha.framework.service.BaseService;
import sha.framework.util.FileReaderUtil;
import sha.work.common.CommonConstants;
import sha.work.entity.out.Loto6AnalysisP2Out;
import sha.work.entity.out.Loto6EstimateDataOut;
import sha.work.entity.out.Loto6SixAnalysisOut;
import sha.work.entity.query.Loto6SixQuery;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.exception.TKRKScreenException;
import sha.work.util.FileUtil;

@Service
public class Loto6EstimateService extends BaseService {
	
	@Autowired
	private ObjectMapper objMapper;
	
	public Loto6EstimateDataOut getEstimateData(Loto6EstimateDataOut dataOut) {
		dataOut.setLoto6AnalysisP2Out(getLoto6AnalysisP2OutData());
		dataOut.setLoto6SixAnalysisOut(getSixAnaLysisData()); 
		estimateRank(dataOut);
		
		return dataOut;
	}

	/** DB access class. */
	private Loto6AnalysisP2Out getLoto6AnalysisP2OutData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto6P2DataFileJson());
			return objMapper.readValue(data, Loto6AnalysisP2Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private Loto6SixAnalysisOut getSixAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto6SixDataFileJson());
			return objMapper.readValue(data, Loto6SixAnalysisOut.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private void estimateRank(Loto6EstimateDataOut dataOut) {

		dataOut.getLoto6AnalysisP2Out().setSummaryTotalAvgList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryTotalAvgList()));
		int[] values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getTotalAvg(), 
											dataOut.getLoto6AnalysisP2Out().getSummaryTotalAvgList());
		dataOut.setTotalAvgRank(values[0]);
		dataOut.setTotalAvgPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryMaxMinDiffList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryMaxMinDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxMinDiff(), 
									dataOut.getLoto6AnalysisP2Out().getSummaryMaxMinDiffList());
		dataOut.setMaxMinDiffRank(values[0]);
		dataOut.setMaxMinDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryMaxNumDiffList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryMaxNumDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxNumDiff(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryMaxNumDiffList());
		dataOut.setMaxNumDiffRank(values[0]);
		dataOut.setMaxNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryMinNumDiffList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryMinNumDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMinNumDiff(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryMinNumDiffList());
		dataOut.setMinNumDiffRank(values[0]);
		dataOut.setMinNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryNumDiffAvgList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryNumDiffAvgList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getNumDiffAvg(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryNumDiffAvgList());
		dataOut.setNumDiffAvgRank(values[0]);
		dataOut.setNumDiffAvgPt(values[1]);;
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryEvenNumCntList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryEvenNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getEvenNumCnt(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryEvenNumCntList());
		dataOut.setEvenNumCntRank(values[0]);
		dataOut.setEvenNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryOddNumCntList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryOddNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getOddNumCnt(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryOddNumCntList());
		dataOut.setOddNumCntRank(values[0]);
		dataOut.setOddNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummarySerialNumCntList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummarySerialNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getSerialNumCnt(), 
				dataOut.getLoto6AnalysisP2Out().getSummarySerialNumCntList());
		dataOut.setSerialNumCntRank(values[0]);
		dataOut.setSerialNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryLeftAreaNumCntList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryLeftAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getLeftAreaNumCnt(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryLeftAreaNumCntList());
		dataOut.setLeftAreaNumCntRank(values[0]);
		dataOut.setLeftAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryCenterAreaNumCntList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryCenterAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getCenterAreaNumCnt(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryCenterAreaNumCntList());
		dataOut.setCenterAreaNumCntRank(values[0]);
		dataOut.setCenterAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto6AnalysisP2Out().setSummaryRightAreaNumCntList(
				subList(dataOut.getLoto6AnalysisP2Out().getSummaryRightAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getRightAreaNumCnt(), 
				dataOut.getLoto6AnalysisP2Out().getSummaryRightAreaNumCntList());
		dataOut.setRightAreaNumCntRank(values[0]);
		dataOut.setRightAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		
		dataOut.setL1List(convertSixData(dataOut.getLoto6SixAnalysisOut().getSixList(),
												Loto6SixQuery::getL1Cnt));
		values = getBaseRankAndPt(dataOut.getL1(), dataOut.getL1List());
		dataOut.setL1Rank(values[0]);
		dataOut.setL1Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL2List(convertSixData(dataOut.getLoto6SixAnalysisOut().getSixList(),
				Loto6SixQuery::getL2Cnt));
		values = getBaseRankAndPt(dataOut.getL2(), dataOut.getL2List());
		dataOut.setL2Rank(values[0]);
		dataOut.setL2Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL3List(convertSixData(dataOut.getLoto6SixAnalysisOut().getSixList(),
				Loto6SixQuery::getL3Cnt));
		values = getBaseRankAndPt(dataOut.getL3(), dataOut.getL3List());
		dataOut.setL3Rank(values[0]);
		dataOut.setL3Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL4List(convertSixData(dataOut.getLoto6SixAnalysisOut().getSixList(),
				Loto6SixQuery::getL4Cnt));
		values = getBaseRankAndPt(dataOut.getL4(), dataOut.getL4List());
		dataOut.setL4Rank(values[0]);
		dataOut.setL4Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL5List(convertSixData(dataOut.getLoto6SixAnalysisOut().getSixList(),
				Loto6SixQuery::getL5Cnt));
		values = getBaseRankAndPt(dataOut.getL5(), dataOut.getL5List());
		dataOut.setL5Rank(values[0]);
		dataOut.setL5Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL6List(convertSixData(dataOut.getLoto6SixAnalysisOut().getSixList(),
				Loto6SixQuery::getL6Cnt));
		values = getBaseRankAndPt(dataOut.getL6(), dataOut.getL6List());
		dataOut.setL6Rank(values[0]);
		dataOut.setL6Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

	}
	
	private int[] getBaseRankAndPt(int value, List<NumberAndTurnsQuery> turns) {
		int[] values = new int[] {0, 10};
		for(NumberAndTurnsQuery turn : turns) {
			if(value == Integer.valueOf(turn.getNumberName())) {
				break;
			}
			values[0]++;
			values[1]--;
		}
		
		return values;
	}
	
	private List<NumberAndTurnsQuery> convertSixData(List<Loto6SixQuery> sixTurns, 
			Function<Loto6SixQuery, Integer> elmentFunction) {
		Comparator<Loto6SixQuery> l1Comparator = Comparator.comparing(elmentFunction).reversed();
		List<Loto6SixQuery> sevenList = sixTurns
											.stream()
							                .sorted(l1Comparator)
							                .collect(Collectors.toList());
		List<NumberAndTurnsQuery> lList = new ArrayList<>();
		int idx = 0;
		for(Loto6SixQuery loto6SixQuery : sevenList) {
			idx++;
			NumberAndTurnsQuery turn = new NumberAndTurnsQuery();
			turn.setNumberName(String.valueOf(loto6SixQuery.getNumberName()));
			turn.setNumberValue(elmentFunction.apply(loto6SixQuery));
			lList.add(turn);
			if(idx >= CommonConstants.MAX_ESTIMATE_RANKS) {
				break;
			}
		}
		
		
		return lList;
	}
	
	private List<NumberAndTurnsQuery> subList(List<NumberAndTurnsQuery> list) {
		if(list.size() > CommonConstants.MAX_ESTIMATE_RANKS) {
			return list.subList(0,  CommonConstants.MAX_ESTIMATE_RANKS);
		}
		
		return list;
	}
}
