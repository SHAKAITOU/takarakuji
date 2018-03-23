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
import sha.work.service.loto.Loto7AnalysisBaseDataCreateService;
import sha.work.service.loto.Loto7EstimateService;

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
		Loto7 loto7 = new Loto7();

		try {
			BeanUtils.copyProperties(dataOut, dataIn);
			BeanUtils.copyProperties(loto7, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		dataOut.setEstimateAnalysisBase(service.analysisOnly(loto7));
		dataOut = estimateService.getEstimateData(dataOut);
		mav.addObject("result", dataOut);
		mav.setViewName(ViewConstants.USER_LOTO7ESTIMATE);
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
	

}
