package sha.work.controller.admin;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import sha.work.dto.loto.Loto6;
import sha.work.entity.in.Loto6AddDataIn;
import sha.work.service.batch.Loto6BatchService;
import sha.work.service.loto.Loto6AddService;
import sha.work.util.LotoUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto6AddController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private Loto6AddService service;
	
	@Autowired
	private Loto6BatchService batchService;


	@RequestMapping(path=UrlConstants.ADMIN_LOTO6ADD, method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		Loto6AddDataIn dataIn = initLoto6AddDataIn();

		
		mav.addObject("result", dataIn);
		mav.setViewName(ViewConstants.ADMIN_LOTO6ADD);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.ADMIN_LOTO6ADD, method=RequestMethod.POST)
	public ModelAndView post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		Loto6 loto6 = setLoto6AddDataIn(allRequestParams);
		service.add(loto6);
		batchService.batch();
		
		Loto6AddDataIn dataIn = initLoto6AddDataIn();

		mav.addObject("result", dataIn);
		mav.setViewName(ViewConstants.ADMIN_LOTO6ADD);
		return mav;
	}
	
	private Loto6AddDataIn initLoto6AddDataIn() {
		Loto6AddDataIn dataIn = new Loto6AddDataIn();
		dataIn.setLastTurn(service.getLastTurn());
		Loto6 newTurn = new Loto6();
		newTurn.setOpenDt(LotoUtil.getNextLoto6OpenDt(dataIn.getLastTurn().getOpenDt()));
		newTurn.setTurn(dataIn.getLastTurn().getTurn()+1);
		dataIn.setNewTurn(newTurn);
		return dataIn;
	}
	
	private Loto6 setLoto6AddDataIn(Map<String,String> allRequestParams) {
		Loto6 newTurn = new Loto6();
		newTurn.setOpenDt(allRequestParams.get("openDt"));
		newTurn.setTurn(Integer.valueOf(allRequestParams.get("turn")));
		newTurn.setL1(Integer.valueOf(allRequestParams.get("l1")));
		newTurn.setL2(Integer.valueOf(allRequestParams.get("l2")));
		newTurn.setL3(Integer.valueOf(allRequestParams.get("l3")));
		newTurn.setL4(Integer.valueOf(allRequestParams.get("l4")));
		newTurn.setL5(Integer.valueOf(allRequestParams.get("l5")));
		newTurn.setL6(Integer.valueOf(allRequestParams.get("l6")));
		newTurn.setB1(Integer.valueOf(allRequestParams.get("b1")));
		
		return newTurn;
	}
	
	

}
