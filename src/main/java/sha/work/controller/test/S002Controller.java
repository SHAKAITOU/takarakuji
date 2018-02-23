package sha.work.controller.test;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sha.framework.controller.ScreenBaseController;
import sha.framework.util.DateCommonUtil;
import sha.framework.util.JsonLogCommonUtil;
import sha.framework.util.MessageSourceUtil;
import sha.framework.util.UserInfoUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping("/S002")
public class S002Controller extends ScreenBaseController{
	
	@Autowired
	private UserInfoUtil userInfoUtil;
	
	@Autowired
	private MessageSourceUtil msg;
	
	@Autowired
	private DateCommonUtil dateUtil;
	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private Validator validator;


	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView exapmle(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response)  {

//		S002InputData input = new S002InputData();
//		
//		input = (S002InputData)userInfoUtil.initInfo(1, input);
//		
//		Set<ConstraintViolation<S002InputData>> result =  validator.validate(input);
//
//
//		
//		S002OutputData output = new S002OutputData();
//
		ModelAndView mav = new ModelAndView();
//		mav.addObject("s002InputData", input);
//
//
//		
		//WebContext context2 = new WebContext(request, response, context, Locale.JAPAN);
		//output.setMsg1(msg.getContext(loc, "MSG001"));
		//output.setMsg2(msg.getContext(loc, "MSG002", "aaa"));
		//output.setMsg3(dateUtil.formatDate(new Date(), DateCommonUtil.DATE_FORMAT_YYYYSMMSDD_YOUBI, loc));
//		
//		//output.setMsg1(msg.getMessage("MSG001", null, Locale.JAPANESE));
//		
//		output.setInputBaseData(input);
//		
//		mav.addObject("output", output);
		mav.setViewName("test/S002");
//		
		jsonLog.info("REQ0001", "TRC0002", "Json log test msg.", request, response, null);
		
		return mav;
	}

	

}
