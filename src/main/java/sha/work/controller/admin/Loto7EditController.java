package sha.work.controller.admin;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import sha.work.entity.in.Loto7AddDataIn;
import sha.work.enums.ExecuteReturnType;
import sha.work.service.batch.Loto7BatchService;
import sha.work.service.loto.Loto7AddOrEditService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto7EditController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private Loto7AddOrEditService service;
	
	@Autowired
	private Loto7BatchService batchService;


	@RequestMapping(path=UrlConstants.ADMIN_LOTO7EDIT, method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		Loto7AddDataIn dataIn = initLoto7AddDataIn();

		
		mav.addObject("result", dataIn);
		mav.addObject("returnType", ExecuteReturnType.DEFAULT.getId());
		mav.setViewName(ViewConstants.ADMIN_LOTO7EDIT);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.ADMIN_LOTO7EDIT, method=RequestMethod.POST, 
						produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		Loto7 loto7 = setLoto7AddDataIn(allRequestParams);
		
		boolean rsFlg = service.edit(loto7);
		if (rsFlg) {
			batchService.batch();
			return new ResponseEntity<Object>(ExecuteReturnType.OK, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(ExecuteReturnType.NG, HttpStatus.OK);
		}
	}
	
	private Loto7AddDataIn initLoto7AddDataIn() {
		Loto7AddDataIn dataIn = new Loto7AddDataIn();
		Loto7 newTurn = new Loto7();
		dataIn.setNewTurn(newTurn);
		return dataIn;
	}
	
	private Loto7 setLoto7AddDataIn(Map<String,String> allRequestParams) {
		Loto7 newTurn = new Loto7();
		newTurn.setTurn(Integer.valueOf(allRequestParams.get("turn")));
		newTurn.setL1(Integer.valueOf(allRequestParams.get("l1")));
		newTurn.setL2(Integer.valueOf(allRequestParams.get("l2")));
		newTurn.setL3(Integer.valueOf(allRequestParams.get("l3")));
		newTurn.setL4(Integer.valueOf(allRequestParams.get("l4")));
		newTurn.setL5(Integer.valueOf(allRequestParams.get("l5")));
		newTurn.setL6(Integer.valueOf(allRequestParams.get("l6")));
		newTurn.setL7(Integer.valueOf(allRequestParams.get("l7")));
		newTurn.setB1(Integer.valueOf(allRequestParams.get("b1")));
		newTurn.setB2(Integer.valueOf(allRequestParams.get("b2")));
		
		return newTurn;
	}
	
	

}
