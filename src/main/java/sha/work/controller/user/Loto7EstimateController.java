package sha.work.controller.user;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;

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

import sha.framework.controller.ScreenBaseController;
import sha.framework.exception.TKRKScreenException;
import sha.framework.util.LogCommonUtil;
import sha.work.common.UrlConstants;
import sha.work.common.ViewConstants;
import sha.work.dto.loto.Loto7;
import sha.work.entity.in.Loto7EstimateDataIn;
import sha.work.entity.out.Loto7EstimateDataOut;
import sha.work.enums.ExecuteReturnType;
import sha.work.service.loto.Loto7AddOrEditService;
import sha.work.service.loto.Loto7AnalysisBaseDataCreateService;
import sha.work.service.loto.Loto7EstimateService;
import sha.work.util.LotoUtil;

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
	private Loto7AddOrEditService loto7Service;
	
	@Autowired
	private Loto7AnalysisBaseDataCreateService service;
	
	@Autowired
	private Loto7EstimateService estimateService;


	@RequestMapping(path=UrlConstants.USER_LOTO7ESTIMATE, method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		Loto7EstimateDataIn dataIn = initLoto7EstimateDataIn();
		Loto7EstimateDataOut dataOut = new Loto7EstimateDataOut();
		
		try {
			BeanUtils.copyProperties(dataOut, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		dataOut.setAnalysisComplete(ExecuteReturnType.NG.getId());
		
		mav.addObject("result", dataOut);
		mav.setViewName(ViewConstants.USER_LOTO7ESTIMATE);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.USER_LOTO7ESTIMATE, method=RequestMethod.POST)
	public ModelAndView post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		Loto7EstimateDataIn dataIn = searchLoto7EstimateDataIn(allRequestParams);

		Loto7EstimateDataOut dataOut = new Loto7EstimateDataOut();

		try {
			BeanUtils.copyProperties(dataOut, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		dataOut.setEstimateAnalysisBase(service.analysisOnly(dataOut.getEstTurn()));
		dataOut = estimateService.getEstimateData(dataOut);
		dataOut.setAnalysisComplete(ExecuteReturnType.OK.getId());
		
		mav.addObject("result", dataOut);
		mav.setViewName(ViewConstants.USER_LOTO7ESTIMATE);
		return mav;
	}
	
	private Loto7EstimateDataIn initLoto7EstimateDataIn() {
		Loto7EstimateDataIn dataIn = new Loto7EstimateDataIn();
		Loto7 lastTurn = loto7Service.getLastTurn();
		Loto7 estTurn = new Loto7();
		estTurn.setTurn(lastTurn.getTurn()+1);
		estTurn.setOpenDt(LotoUtil.getNextLoto7OpenDt(lastTurn.getOpenDt()));
		dataIn.setEstTurn(estTurn);;
		
		
//		dataIn.setL1(1);
//		dataIn.setL2(9);
//		dataIn.setL3(14);
//		dataIn.setL4(21);
//		dataIn.setL5(28);
//		dataIn.setL6(31);
//		dataIn.setL7(34);
		
		return dataIn;
	}
	
	private Loto7EstimateDataIn searchLoto7EstimateDataIn(Map<String,String> allRequestParams) {
		Loto7EstimateDataIn dataIn = new Loto7EstimateDataIn();
		Loto7 estTurn = new Loto7();
		estTurn.setTurn(Integer.valueOf(allRequestParams.get("turn")));
		estTurn.setOpenDt(allRequestParams.get("openDt"));
		estTurn.setL1(Integer.valueOf(allRequestParams.get("l1")));
		estTurn.setL2(Integer.valueOf(allRequestParams.get("l2")));
		estTurn.setL3(Integer.valueOf(allRequestParams.get("l3")));
		estTurn.setL4(Integer.valueOf(allRequestParams.get("l4")));
		estTurn.setL5(Integer.valueOf(allRequestParams.get("l5")));
		estTurn.setL6(Integer.valueOf(allRequestParams.get("l6")));
		estTurn.setL7(Integer.valueOf(allRequestParams.get("l7")));
		dataIn.setEstTurn(estTurn);
		
		return dataIn;
	}
	

}
