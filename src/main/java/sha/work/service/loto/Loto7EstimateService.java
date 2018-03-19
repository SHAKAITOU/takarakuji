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
import sha.work.entity.out.Loto7AnalysisP2Out;
import sha.work.entity.out.Loto7EstimateDataOut;
import sha.work.entity.out.Loto7SevenAnalysisOut;
import sha.work.entity.query.Loto7SevenQuery;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.exception.TKRKScreenException;
import sha.work.util.FileUtil;

@Service
public class Loto7EstimateService extends BaseService {
	
	@Autowired
	private ObjectMapper objMapper;
	
	public Loto7EstimateDataOut getEstimateData(Loto7EstimateDataOut dataOut) {
		dataOut.setLoto7AnalysisP2Out(getLoto7AnalysisP2OutData());
		dataOut.setLoto7SevenAnalysisOut(getSevenAnaLysisData()); 
		estimateRank(dataOut);
		
		return dataOut;
	}


	private Loto7AnalysisP2Out getLoto7AnalysisP2OutData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto7P2DataFileJson());
			return objMapper.readValue(data, Loto7AnalysisP2Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private Loto7SevenAnalysisOut getSevenAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto7SevenDataFileJson());
			return objMapper.readValue(data, Loto7SevenAnalysisOut.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private void estimateRank(Loto7EstimateDataOut dataOut) {

		dataOut.getLoto7AnalysisP2Out().setSummaryTotalAvgList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryTotalAvgList()));
		int[] values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getTotalAvg(), 
											dataOut.getLoto7AnalysisP2Out().getSummaryTotalAvgList());
		dataOut.setTotalAvgRank(values[0]);
		dataOut.setTotalAvgPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryMaxMinDiffList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryMaxMinDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxMinDiff(), 
									dataOut.getLoto7AnalysisP2Out().getSummaryMaxMinDiffList());
		dataOut.setMaxMinDiffRank(values[0]);
		dataOut.setMaxMinDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryMaxNumDiffList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryMaxNumDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxNumDiff(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryMaxNumDiffList());
		dataOut.setMaxNumDiffRank(values[0]);
		dataOut.setMaxNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryMinNumDiffList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryMinNumDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMinNumDiff(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryMinNumDiffList());
		dataOut.setMinNumDiffRank(values[0]);
		dataOut.setMinNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryNumDiffAvgList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryNumDiffAvgList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getNumDiffAvg(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryNumDiffAvgList());
		dataOut.setNumDiffAvgRank(values[0]);
		dataOut.setNumDiffAvgPt(values[1]);;
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryEvenNumCntList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryEvenNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getEvenNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryEvenNumCntList());
		dataOut.setEvenNumCntRank(values[0]);
		dataOut.setEvenNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryOddNumCntList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryOddNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getOddNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryOddNumCntList());
		dataOut.setOddNumCntRank(values[0]);
		dataOut.setOddNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummarySerialNumCntList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummarySerialNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getSerialNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummarySerialNumCntList());
		dataOut.setSerialNumCntRank(values[0]);
		dataOut.setSerialNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryLeftAreaNumCntList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryLeftAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getLeftAreaNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryLeftAreaNumCntList());
		dataOut.setLeftAreaNumCntRank(values[0]);
		dataOut.setLeftAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryCenterAreaNumCntList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryCenterAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getCenterAreaNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryCenterAreaNumCntList());
		dataOut.setCenterAreaNumCntRank(values[0]);
		dataOut.setCenterAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getLoto7AnalysisP2Out().setSummaryRightAreaNumCntList(
				subList(dataOut.getLoto7AnalysisP2Out().getSummaryRightAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getRightAreaNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryRightAreaNumCntList());
		dataOut.setRightAreaNumCntRank(values[0]);
		dataOut.setRightAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		
		dataOut.setL1List(convertSevenData(dataOut.getLoto7SevenAnalysisOut().getSevenList(),
												Loto7SevenQuery::getL1Cnt));
		values = getBaseRankAndPt(dataOut.getL1(), dataOut.getL1List());
		dataOut.setL1Rank(values[0]);
		dataOut.setL1Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL2List(convertSevenData(dataOut.getLoto7SevenAnalysisOut().getSevenList(),
				Loto7SevenQuery::getL2Cnt));
		values = getBaseRankAndPt(dataOut.getL2(), dataOut.getL2List());
		dataOut.setL2Rank(values[0]);
		dataOut.setL2Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL3List(convertSevenData(dataOut.getLoto7SevenAnalysisOut().getSevenList(),
				Loto7SevenQuery::getL3Cnt));
		values = getBaseRankAndPt(dataOut.getL3(), dataOut.getL3List());
		dataOut.setL3Rank(values[0]);
		dataOut.setL3Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL4List(convertSevenData(dataOut.getLoto7SevenAnalysisOut().getSevenList(),
				Loto7SevenQuery::getL4Cnt));
		values = getBaseRankAndPt(dataOut.getL4(), dataOut.getL4List());
		dataOut.setL4Rank(values[0]);
		dataOut.setL4Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL5List(convertSevenData(dataOut.getLoto7SevenAnalysisOut().getSevenList(),
				Loto7SevenQuery::getL5Cnt));
		values = getBaseRankAndPt(dataOut.getL5(), dataOut.getL5List());
		dataOut.setL5Rank(values[0]);
		dataOut.setL5Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL6List(convertSevenData(dataOut.getLoto7SevenAnalysisOut().getSevenList(),
				Loto7SevenQuery::getL6Cnt));
		values = getBaseRankAndPt(dataOut.getL6(), dataOut.getL6List());
		dataOut.setL6Rank(values[0]);
		dataOut.setL6Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL7List(convertSevenData(dataOut.getLoto7SevenAnalysisOut().getSevenList(),
				Loto7SevenQuery::getL7Cnt));
		values = getBaseRankAndPt(dataOut.getL7(), dataOut.getL7List());
		dataOut.setL7Rank(values[0]);
		dataOut.setL7Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

	}
	
	private int[] getBaseRankAndPt(int value, List<NumberAndTurnsQuery> turns) {
		int[] values = new int[] {0, 10};
		for(NumberAndTurnsQuery turn : turns) {
			if(value == Integer.valueOf(turn.getNumberName())) {
				break;
			}
			values[1]--;
			values[0]++;
		}
		
		return values;
	}
	
	private List<NumberAndTurnsQuery> convertSevenData(List<Loto7SevenQuery> sevenTurns, 
			Function<Loto7SevenQuery, Integer> elmentFunction) {
		Comparator<Loto7SevenQuery> l1Comparator = Comparator.comparing(elmentFunction).reversed();
		List<Loto7SevenQuery> sevenList = sevenTurns
											.stream()
							                .sorted(l1Comparator)
							                .collect(Collectors.toList());
		List<NumberAndTurnsQuery> lList = new ArrayList<>();
		int idx = 0;
		for(Loto7SevenQuery loto7SevenQuery : sevenList) {
			idx++;
			NumberAndTurnsQuery turn = new NumberAndTurnsQuery();
			turn.setNumberName(String.valueOf(loto7SevenQuery.getNumberName()));
			turn.setNumberValue(elmentFunction.apply(loto7SevenQuery));
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
