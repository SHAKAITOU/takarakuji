package sha.work.controller.loto;

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
import sha.work.dto.loto.Loto6;
import sha.work.entity.in.Loto6EstimateDataIn;
import sha.work.entity.out.Loto6EstimateDataOut;
import sha.work.service.batch.Loto6AnalysisBaseDataCreateService;
import sha.work.service.loto.Loto6EstimateService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto6EstimateController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private Loto6AnalysisBaseDataCreateService service;
	
	@Autowired
	private Loto6EstimateService estimateService;


	@RequestMapping(path="/loto/loto6Estimate", method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		Loto6EstimateDataIn dataIn = initLoto6EstimateDataIn();
		Loto6EstimateDataOut dataOut = new Loto6EstimateDataOut();
		
		try {
			BeanUtils.copyProperties(dataOut, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		mav.addObject("result", dataOut);
		mav.setViewName("loto/loto6Estimate");
		return mav;
	}
	
	@RequestMapping(path="/loto/loto6Estimate", method=RequestMethod.POST)
	public ModelAndView post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		Loto6EstimateDataIn dataIn = searchLoto6EstimateDataIn(allRequestParams);

		Loto6EstimateDataOut dataOut = new Loto6EstimateDataOut();
		Loto6 loto6 = new Loto6();

		try {
			BeanUtils.copyProperties(dataOut, dataIn);
			BeanUtils.copyProperties(loto6, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		
		dataOut.setEstimateAnalysisBase(service.analysisOnly(loto6));
		dataOut = estimateService.getEstimateData(dataOut);
		mav.addObject("result", dataOut);
		mav.setViewName("loto/loto6Estimate");
		return mav;
	}
	
	private Loto6EstimateDataIn initLoto6EstimateDataIn() {
		Loto6EstimateDataIn dataIn = new Loto6EstimateDataIn();
		dataIn.setL1(1);
		dataIn.setL2(9);
		dataIn.setL3(14);
		dataIn.setL4(21);
		dataIn.setL5(28);
		dataIn.setL6(31);
		dataIn.setB1(7);
		
		return dataIn;
	}
	
	private Loto6EstimateDataIn searchLoto6EstimateDataIn(Map<String,String> allRequestParams) {
		Loto6EstimateDataIn dataIn = new Loto6EstimateDataIn();
		dataIn.setL1(Integer.valueOf(allRequestParams.get("l1")));
		dataIn.setL2(Integer.valueOf(allRequestParams.get("l2")));
		dataIn.setL3(Integer.valueOf(allRequestParams.get("l3")));
		dataIn.setL4(Integer.valueOf(allRequestParams.get("l4")));
		dataIn.setL5(Integer.valueOf(allRequestParams.get("l5")));
		dataIn.setL6(Integer.valueOf(allRequestParams.get("l6")));
		dataIn.setB1(Integer.valueOf(allRequestParams.get("b1")));
		
		return dataIn;
	}
	
	

}
