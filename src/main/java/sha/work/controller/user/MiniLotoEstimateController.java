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
import sha.work.dto.loto.MiniLoto;
import sha.work.entity.in.MiniLotoEstimateDataIn;
import sha.work.entity.out.MiniLotoEstimateDataOut;
import sha.work.service.loto.MiniLotoAnalysisBaseDataCreateService;
import sha.work.service.loto.MiniLotoEstimateService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class MiniLotoEstimateController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private MiniLotoAnalysisBaseDataCreateService service;
	
	@Autowired
	private MiniLotoEstimateService estimateService;


	@RequestMapping(path="/user/miniLotoEstimate", method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		MiniLotoEstimateDataIn dataIn = initMiniLotoEstimateDataIn();
		MiniLotoEstimateDataOut dataOut = new MiniLotoEstimateDataOut();
		
		try {
			BeanUtils.copyProperties(dataOut, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		mav.addObject("result", dataOut);
		mav.setViewName("user/miniLotoEstimate");
		return mav;
	}
	
	@RequestMapping(path="/user/miniLotoEstimate", method=RequestMethod.POST)
	public ModelAndView post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		MiniLotoEstimateDataIn dataIn = searchMiniLotoEstimateDataIn(allRequestParams);

		MiniLotoEstimateDataOut dataOut = new MiniLotoEstimateDataOut();
		MiniLoto miniLoto = new MiniLoto();

		try {
			BeanUtils.copyProperties(dataOut, dataIn);
			BeanUtils.copyProperties(miniLoto, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		dataOut.setEstimateAnalysisBase(service.analysisOnly(miniLoto));
		dataOut = estimateService.getEstimateData(dataOut);
		mav.addObject("result", dataOut);
		mav.setViewName("user/miniLotoEstimate");
		return mav;
	}
	
	private MiniLotoEstimateDataIn initMiniLotoEstimateDataIn() {
		MiniLotoEstimateDataIn dataIn = new MiniLotoEstimateDataIn();
		dataIn.setL1(1);
		dataIn.setL2(9);
		dataIn.setL3(14);
		dataIn.setL4(21);
		dataIn.setL5(28);
		dataIn.setB1(7);
		
		return dataIn;
	}
	
	private MiniLotoEstimateDataIn searchMiniLotoEstimateDataIn(Map<String,String> allRequestParams) {
		MiniLotoEstimateDataIn dataIn = new MiniLotoEstimateDataIn();
		dataIn.setL1(Integer.valueOf(allRequestParams.get("l1")));
		dataIn.setL2(Integer.valueOf(allRequestParams.get("l2")));
		dataIn.setL3(Integer.valueOf(allRequestParams.get("l3")));
		dataIn.setL4(Integer.valueOf(allRequestParams.get("l4")));
		dataIn.setL5(Integer.valueOf(allRequestParams.get("l5")));
		dataIn.setB1(Integer.valueOf(allRequestParams.get("b1")));
		
		return dataIn;
	}
	
	

}
