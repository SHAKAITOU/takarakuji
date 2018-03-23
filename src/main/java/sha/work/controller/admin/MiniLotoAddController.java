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
import sha.work.dto.loto.MiniLoto;
import sha.work.entity.in.MiniLotoAddDataIn;
import sha.work.service.loto.MiniLotoAddService;
import sha.work.util.LotoUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class MiniLotoAddController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private MiniLotoAddService service;


	@RequestMapping(path=UrlConstants.ADMIN_MINILOTOADD, method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		MiniLotoAddDataIn dataIn = initMiniLotoAddDataIn();

		
		mav.addObject("result", dataIn);
		mav.setViewName(ViewConstants.ADMIN_MINILOTOADD);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.ADMIN_MINILOTOADD, method=RequestMethod.POST)
	public ModelAndView post(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		MiniLoto miniLoto = setMiniLotoAddDataIn(allRequestParams);
		service.add(miniLoto);
		
		MiniLotoAddDataIn dataIn = initMiniLotoAddDataIn();

		mav.addObject("result", dataIn);
		mav.setViewName(ViewConstants.ADMIN_MINILOTOADD);
		return mav;
	}
	
	private MiniLotoAddDataIn initMiniLotoAddDataIn() {
		MiniLotoAddDataIn dataIn = new MiniLotoAddDataIn();
		dataIn.setLastTurn(service.getLastTurn());
		MiniLoto newTurn = new MiniLoto();
		newTurn.setOpenDt(LotoUtil.getNextMiniLotoOpenDt(dataIn.getLastTurn().getOpenDt()));
		newTurn.setTurn(dataIn.getLastTurn().getTurn()+1);
		dataIn.setNewTurn(newTurn);
		return dataIn;
	}
	
	private MiniLoto setMiniLotoAddDataIn(Map<String,String> allRequestParams) {
		MiniLoto newTurn = new MiniLoto();
		newTurn.setOpenDt(allRequestParams.get("openDt"));
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
