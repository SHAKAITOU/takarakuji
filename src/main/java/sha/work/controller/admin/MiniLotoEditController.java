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
import sha.work.dto.loto.MiniLoto;
import sha.work.entity.common.ReturnObject;
import sha.work.entity.in.MiniLotoAddDataIn;
import sha.work.enums.ExecuteReturnType;
import sha.work.service.batch.MiniLotoBatchService;
import sha.work.service.loto.MiniLotoAddOrEditService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class MiniLotoEditController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private MiniLotoAddOrEditService service;
	
	@Autowired
	private MiniLotoBatchService batchService;
	
	@Autowired
	private MessageSourceUtil messageSourceUtil;


	@RequestMapping(path=UrlConstants.ADMIN_MINILOTOEDIT, method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		MiniLotoAddDataIn dataIn = initMiniLotoEditDataIn();

		
		mav.addObject("result", dataIn);
		mav.addObject("returnType", ExecuteReturnType.DEFAULT.getId());
		mav.setViewName(ViewConstants.ADMIN_MINILOTOEDIT);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.ADMIN_MINILOTOEDIT_SEARCH, method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> search(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {

		int turn = Integer.valueOf(allRequestParams.get("turn"));
		MiniLoto miniLoto = search(turn);
		
		ReturnObject returnObject = new ReturnObject();
		if (Objects.isNull(miniLoto)) {
			returnObject.setReturnType(ExecuteReturnType.NG.getId());
			returnObject.setReturnMsg(messageSourceUtil.getContext(
					"miniLotoAdd.edit.fail.msg", Integer.toString(turn)));
		} else {
			returnObject.setReturnType(ExecuteReturnType.OK.getId());
			returnObject.setReturnObj(miniLoto);
		}

		return new ResponseEntity<Object>(returnObject, HttpStatus.OK);
	}

	@RequestMapping(path=UrlConstants.ADMIN_MINILOTOEDIT, method=RequestMethod.POST, 
						produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		MiniLoto miniLoto = setMiniLotoEditDataIn(allRequestParams);
		
		boolean rsFlg = service.edit(miniLoto);
		ReturnObject returnObject = new ReturnObject();
		if (rsFlg) {
			batchService.batch();
			returnObject.setReturnType(ExecuteReturnType.OK.getId());
			returnObject.setReturnMsg(messageSourceUtil.getContext(
					"miniLotoAdd.edit.success.msg", Integer.toString(miniLoto.getTurn())));
			returnObject.setReturnObj(service.getByTurn(miniLoto.getTurn()));
		} else {
			returnObject.setReturnType(ExecuteReturnType.NG.getId());
			returnObject.setReturnMsg(messageSourceUtil.getContext(
					"miniLotoAdd.edit.fail.msg", Integer.toString(miniLoto.getTurn())));
		}
		return new ResponseEntity<Object>(returnObject, HttpStatus.OK);
	}
	
	private MiniLotoAddDataIn initMiniLotoEditDataIn() {
		MiniLotoAddDataIn dataIn = new MiniLotoAddDataIn();
		MiniLoto newTurn = new MiniLoto();
		dataIn.setNewTurn(newTurn);
		return dataIn;
	}
	
	private MiniLoto search(int turn) {
		return service.getByTurn(turn);
	}
	
	
	private MiniLoto setMiniLotoEditDataIn(Map<String,String> allRequestParams) {
		MiniLoto newTurn = new MiniLoto();
		newTurn.setTurn(Integer.valueOf(allRequestParams.get("turn")));
		newTurn.setL1(Integer.valueOf(allRequestParams.get("l1")));
		newTurn.setL2(Integer.valueOf(allRequestParams.get("l2")));
		newTurn.setL3(Integer.valueOf(allRequestParams.get("l3")));
		newTurn.setL4(Integer.valueOf(allRequestParams.get("l4")));
		newTurn.setL5(Integer.valueOf(allRequestParams.get("l5")));
		newTurn.setB1(Integer.valueOf(allRequestParams.get("b1")));
		
		return newTurn;
	}
	
	

}
