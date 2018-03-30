package sha.work.controller.admin;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

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
import sha.framework.util.MessageSourceUtil;
import sha.work.common.UrlConstants;
import sha.work.common.ViewConstants;
import sha.work.dto.loto.Loto6;
import sha.work.entity.common.ReturnObject;
import sha.work.entity.in.Loto6AddDataIn;
import sha.work.enums.ExecuteReturnType;
import sha.work.service.batch.Loto6BatchService;
import sha.work.service.loto.Loto6AddOrEditService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto6EditController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private Loto6AddOrEditService service;
	
	@Autowired
	private Loto6BatchService batchService;
	
	@Autowired
	private MessageSourceUtil messageSourceUtil;


	@RequestMapping(path=UrlConstants.ADMIN_LOTO6EDIT, method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		Loto6AddDataIn dataIn = initLoto6EditDataIn();

		
		mav.addObject("result", dataIn);
		mav.addObject("returnType", ExecuteReturnType.DEFAULT.getId());
		mav.setViewName(ViewConstants.ADMIN_LOTO6EDIT);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.ADMIN_LOTO6EDIT_SEARCH, method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> search(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {

		int turn = Integer.valueOf(allRequestParams.get("turn"));
		Loto6 loto6 = search(turn);
		
		ReturnObject returnObject = new ReturnObject();
		if (Objects.isNull(loto6)) {
			returnObject.setReturnType(ExecuteReturnType.NG.getId());
			returnObject.setReturnMsg(messageSourceUtil.getContext(
					"loto6Add.edit.fail.msg", Integer.toString(turn)));
		} else {
			returnObject.setReturnType(ExecuteReturnType.OK.getId());
			returnObject.setReturnObj(loto6);
		}

		return new ResponseEntity<Object>(returnObject, HttpStatus.OK);
	}

	@RequestMapping(path=UrlConstants.ADMIN_LOTO6EDIT, method=RequestMethod.POST, 
						produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		Loto6 loto6 = setLoto6EditDataIn(allRequestParams);
		
		boolean rsFlg = service.edit(loto6);
		ReturnObject returnObject = new ReturnObject();
		if (rsFlg) {
			batchService.batch();
			returnObject.setReturnType(ExecuteReturnType.OK.getId());
			returnObject.setReturnMsg(messageSourceUtil.getContext(
					"loto6Add.edit.success.msg", Integer.toString(loto6.getTurn())));
			returnObject.setReturnObj(service.getByTurn(loto6.getTurn()));
		} else {
			returnObject.setReturnType(ExecuteReturnType.NG.getId());
			returnObject.setReturnMsg(messageSourceUtil.getContext(
					"loto6Add.edit.fail.msg", Integer.toString(loto6.getTurn())));
		}
		return new ResponseEntity<Object>(returnObject, HttpStatus.OK);
	}
	
	private Loto6AddDataIn initLoto6EditDataIn() {
		Loto6AddDataIn dataIn = new Loto6AddDataIn();
		Loto6 newTurn = new Loto6();
		dataIn.setNewTurn(newTurn);
		return dataIn;
	}
	
	private Loto6 search(int turn) {
		return service.getByTurn(turn);
	}
	
	
	private Loto6 setLoto6EditDataIn(Map<String,String> allRequestParams) {
		Loto6 newTurn = new Loto6();
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
