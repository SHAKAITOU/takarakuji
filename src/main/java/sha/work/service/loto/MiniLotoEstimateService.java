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
import sha.work.entity.out.MiniLotoAnalysisP2Out;
import sha.work.entity.out.MiniLotoEstimateDataOut;
import sha.work.entity.out.MiniLotoFiveAnalysisOut;
import sha.work.entity.query.MiniLotoFiveQuery;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.exception.TKRKScreenException;
import sha.work.util.FileUtil;

@Service
public class MiniLotoEstimateService extends BaseService {
	
	@Autowired
	private ObjectMapper objMapper;
	
	public MiniLotoEstimateDataOut getEstimateData(MiniLotoEstimateDataOut dataOut) {
		dataOut.setMiniLotoAnalysisP2Out(getMiniLotoAnalysisP2OutData());
		dataOut.setMiniLotoFiveAnalysisOut(getFiveAnaLysisData()); 
		estimateRank(dataOut);
		
		return dataOut;
	}

	/** DB access class. */
	private MiniLotoAnalysisP2Out getMiniLotoAnalysisP2OutData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getMiniLotoP2DataFileJson());
			return objMapper.readValue(data, MiniLotoAnalysisP2Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private MiniLotoFiveAnalysisOut getFiveAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getMiniLotoFiveDataFileJson());
			return objMapper.readValue(data, MiniLotoFiveAnalysisOut.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private void estimateRank(MiniLotoEstimateDataOut dataOut) {

		dataOut.getMiniLotoAnalysisP2Out().setSummaryTotalAvgList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryTotalAvgList()));
		int[] values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getTotalAvg(), 
											dataOut.getMiniLotoAnalysisP2Out().getSummaryTotalAvgList());
		dataOut.setTotalAvgRank(values[0]);
		dataOut.setTotalAvgPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryMaxMinDiffList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryMaxMinDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxMinDiff(), 
									dataOut.getMiniLotoAnalysisP2Out().getSummaryMaxMinDiffList());
		dataOut.setMaxMinDiffRank(values[0]);
		dataOut.setMaxMinDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryMaxNumDiffList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryMaxNumDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxNumDiff(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryMaxNumDiffList());
		dataOut.setMaxNumDiffRank(values[0]);
		dataOut.setMaxNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryMinNumDiffList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryMinNumDiffList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMinNumDiff(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryMinNumDiffList());
		dataOut.setMinNumDiffRank(values[0]);
		dataOut.setMinNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryNumDiffAvgList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryNumDiffAvgList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getNumDiffAvg(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryNumDiffAvgList());
		dataOut.setNumDiffAvgRank(values[0]);
		dataOut.setNumDiffAvgPt(values[1]);;
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryEvenNumCntList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryEvenNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getEvenNumCnt(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryEvenNumCntList());
		dataOut.setEvenNumCntRank(values[0]);
		dataOut.setEvenNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryOddNumCntList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryOddNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getOddNumCnt(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryOddNumCntList());
		dataOut.setOddNumCntRank(values[0]);
		dataOut.setOddNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummarySerialNumCntList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummarySerialNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getSerialNumCnt(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummarySerialNumCntList());
		dataOut.setSerialNumCntRank(values[0]);
		dataOut.setSerialNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryLeftAreaNumCntList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryLeftAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getLeftAreaNumCnt(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryLeftAreaNumCntList());
		dataOut.setLeftAreaNumCntRank(values[0]);
		dataOut.setLeftAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryCenterAreaNumCntList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryCenterAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getCenterAreaNumCnt(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryCenterAreaNumCntList());
		dataOut.setCenterAreaNumCntRank(values[0]);
		dataOut.setCenterAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.getMiniLotoAnalysisP2Out().setSummaryRightAreaNumCntList(
				subList(dataOut.getMiniLotoAnalysisP2Out().getSummaryRightAreaNumCntList()));
		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getRightAreaNumCnt(), 
				dataOut.getMiniLotoAnalysisP2Out().getSummaryRightAreaNumCntList());
		dataOut.setRightAreaNumCntRank(values[0]);
		dataOut.setRightAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		
		dataOut.setL1List(convertFiveData(dataOut.getMiniLotoFiveAnalysisOut().getFiveList(),
												MiniLotoFiveQuery::getL1Cnt));
		values = getBaseRankAndPt(dataOut.getL1(), dataOut.getL1List());
		dataOut.setL1Rank(values[0]);
		dataOut.setL1Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL2List(convertFiveData(dataOut.getMiniLotoFiveAnalysisOut().getFiveList(),
				MiniLotoFiveQuery::getL2Cnt));
		values = getBaseRankAndPt(dataOut.getL2(), dataOut.getL2List());
		dataOut.setL2Rank(values[0]);
		dataOut.setL2Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL3List(convertFiveData(dataOut.getMiniLotoFiveAnalysisOut().getFiveList(),
				MiniLotoFiveQuery::getL3Cnt));
		values = getBaseRankAndPt(dataOut.getL3(), dataOut.getL3List());
		dataOut.setL3Rank(values[0]);
		dataOut.setL3Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL4List(convertFiveData(dataOut.getMiniLotoFiveAnalysisOut().getFiveList(),
				MiniLotoFiveQuery::getL4Cnt));
		values = getBaseRankAndPt(dataOut.getL4(), dataOut.getL4List());
		dataOut.setL4Rank(values[0]);
		dataOut.setL4Pt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		dataOut.setL5List(convertFiveData(dataOut.getMiniLotoFiveAnalysisOut().getFiveList(),
				MiniLotoFiveQuery::getL5Cnt));
		values = getBaseRankAndPt(dataOut.getL5(), dataOut.getL5List());
		dataOut.setL5Rank(values[0]);
		dataOut.setL5Pt(values[1]);
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
	
	private List<NumberAndTurnsQuery> convertFiveData(List<MiniLotoFiveQuery> fiveTurns, 
			Function<MiniLotoFiveQuery, Integer> elmentFunction) {
		Comparator<MiniLotoFiveQuery> l1Comparator = Comparator.comparing(elmentFunction).reversed();
		List<MiniLotoFiveQuery> sevenList = fiveTurns
											.stream()
							                .sorted(l1Comparator)
							                .collect(Collectors.toList());
		List<NumberAndTurnsQuery> lList = new ArrayList<>();
		int idx = 0;
		for(MiniLotoFiveQuery MiniLotoFiveQuery : sevenList) {
			idx++;
			NumberAndTurnsQuery turn = new NumberAndTurnsQuery();
			turn.setNumberName(String.valueOf(MiniLotoFiveQuery.getNumberName()));
			turn.setNumberValue(elmentFunction.apply(MiniLotoFiveQuery));
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
