package sha.work.controller.loto;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sha.framework.controller.ScreenBaseController;
import sha.framework.exception.TKRKScreenException;
import sha.framework.util.FileReaderUtil;
import sha.framework.util.LogCommonUtil;
import sha.work.dto.loto.Loto7;
import sha.work.entity.in.Loto7EstimateDataIn;
import sha.work.entity.out.Loto7AnalysisP2Out;
import sha.work.entity.out.Loto7EstimateDataOut;
import sha.work.entity.out.Loto7SevenAnalysisOut;
import sha.work.entity.query.Loto7SevenQuery;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.service.batch.Loto7AnalysisBaseDataCreateService;
import sha.work.util.FileUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto7EstimateController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private Loto7AnalysisBaseDataCreateService service;
	
	@Autowired
	private ObjectMapper objMapper;


	@RequestMapping(path="/loto/loto7Estimate", method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		Loto7EstimateDataIn dataIn = initLoto7EstimateDataIn();
		Loto7EstimateDataOut dataOut = new Loto7EstimateDataOut();
		
		try {
			BeanUtils.copyProperties(dataOut, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		mav.addObject("result", dataOut);
		mav.setViewName("loto/loto7Estimate");
		return mav;
	}
	
	@RequestMapping(path="/loto/loto7Estimate", method=RequestMethod.POST)
	public ModelAndView post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		Loto7EstimateDataIn dataIn = searchLoto7EstimateDataIn(allRequestParams);

		Loto7EstimateDataOut dataOut = new Loto7EstimateDataOut();
		Loto7 loto7 = new Loto7();

		try {
			BeanUtils.copyProperties(dataOut, dataIn);
			BeanUtils.copyProperties(loto7, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		dataOut.setEstimateAnalysisBase(service.analysisOnly(loto7));
		dataOut.setLoto7AnalysisP2Out(getLoto7AnalysisP2OutData());
		dataOut.setLoto7SevenAnalysisOut(getSevenAnaLysisData()); 
		estimateRank(dataOut);
		mav.addObject("result", dataOut);
		mav.setViewName("loto/loto7Estimate");
		return mav;
	}
	
	private Loto7EstimateDataIn initLoto7EstimateDataIn() {
		Loto7EstimateDataIn dataIn = new Loto7EstimateDataIn();
		dataIn.setL1(1);
		dataIn.setL2(9);
		dataIn.setL3(14);
		dataIn.setL4(21);
		dataIn.setL5(28);
		dataIn.setL6(31);
		dataIn.setL7(34);
		dataIn.setB1(7);
		dataIn.setB2(8);
		
		return dataIn;
	}
	
	private Loto7EstimateDataIn searchLoto7EstimateDataIn(Map<String,String> allRequestParams) {
		Loto7EstimateDataIn dataIn = new Loto7EstimateDataIn();
		dataIn.setL1(Integer.valueOf(allRequestParams.get("l1")));
		dataIn.setL2(Integer.valueOf(allRequestParams.get("l2")));
		dataIn.setL3(Integer.valueOf(allRequestParams.get("l3")));
		dataIn.setL4(Integer.valueOf(allRequestParams.get("l4")));
		dataIn.setL5(Integer.valueOf(allRequestParams.get("l5")));
		dataIn.setL6(Integer.valueOf(allRequestParams.get("l6")));
		dataIn.setL7(Integer.valueOf(allRequestParams.get("l7")));
		dataIn.setB1(Integer.valueOf(allRequestParams.get("b1")));
		dataIn.setB2(Integer.valueOf(allRequestParams.get("b2")));
		
		return dataIn;
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

		int[] values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getTotalAvg(), 
											dataOut.getLoto7AnalysisP2Out().getSummaryTotalAvgList());
		dataOut.setTotalAvgRank(values[0]);
		dataOut.setTotalAvgPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxMinDiff(), 
									dataOut.getLoto7AnalysisP2Out().getSummaryMaxMinDiffList());
		dataOut.setMaxMinDiffRank(values[0]);
		dataOut.setMaxMinDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMaxNumDiff(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryMaxNumDiffList());
		dataOut.setMaxNumDiffRank(values[0]);
		dataOut.setMaxNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getMinNumDiff(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryMinNumDiffList());
		dataOut.setMinNumDiffRank(values[0]);
		dataOut.setMinNumDiffPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getNumDiffAvg(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryNumDiffAvgList());
		dataOut.setNumDiffAvgRank(values[0]);
		dataOut.setNumDiffAvgPt(values[1]);;
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getEvenNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryEvenNumCntList());
		dataOut.setEvenNumCntRank(values[0]);
		dataOut.setEvenNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getOddNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryOddNumCntList());
		dataOut.setOddNumCntRank(values[0]);
		dataOut.setOddNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getSerialNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummarySerialNumCntList());
		dataOut.setSerialNumCntRank(values[0]);
		dataOut.setSerialNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getLeftAreaNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryLeftAreaNumCntList());
		dataOut.setLeftAreaNumCntRank(values[0]);
		dataOut.setLeftAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

		values = getBaseRankAndPt(dataOut.getEstimateAnalysisBase().getCenterAreaNumCnt(), 
				dataOut.getLoto7AnalysisP2Out().getSummaryCenterAreaNumCntList());
		dataOut.setCenterAreaNumCntRank(values[0]);
		dataOut.setCenterAreaNumCntPt(values[1]);
		dataOut.setTotalPt(dataOut.getTotalPt()+values[1]);

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
			values[0]++;
			values[1]--;
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
		int maxRow = 10;
		int idx = 0;
		for(Loto7SevenQuery loto7SevenQuery : sevenList) {
			idx++;
			NumberAndTurnsQuery turn = new NumberAndTurnsQuery();
			turn.setNumberName(String.valueOf(loto7SevenQuery.getNumberName()));
			turn.setNumberValue(elmentFunction.apply(loto7SevenQuery));
			lList.add(turn);
			if(idx == maxRow) {
				break;
			}
		}
		
		
		return lList;
	}

}
