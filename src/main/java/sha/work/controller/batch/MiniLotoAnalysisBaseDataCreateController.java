package sha.work.controller.batch;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import sha.framework.controller.ScreenBaseController;
import sha.framework.util.JsonLogCommonUtil;
import sha.framework.util.MessageSourceUtil;
import sha.work.exception.TKRKScreenException;
import sha.work.service.batch.MiniLotoAnalysisBaseDataCreateService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping("/batch/createMiniLotoAnalysisBaseData")
public class MiniLotoAnalysisBaseDataCreateController extends ScreenBaseController{
	
	
	@Autowired
	private MessageSourceUtil msg;

	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private MiniLotoAnalysisBaseDataCreateService service;


	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView exapmle(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		service.analysisAll();
		mav.setViewName("/common/success");
		
		return mav;
	}

	

}
