package sha.work.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import sha.framework.controller.ScreenBaseController;
import sha.framework.exception.TKRKScreenException;
import sha.work.common.UrlConstants;
import sha.work.common.ViewConstants;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class UserLoginController extends ScreenBaseController{
	

	@RequestMapping(path=UrlConstants.USER_LOGIN, method=RequestMethod.GET)
	public ModelAndView get(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ViewConstants.LOGIN);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.USER_LOGIN_SUCC, method=RequestMethod.POST)
	public ModelAndView success(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ViewConstants.LOGIN_SUCC);
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.USER_LOGIN, method=RequestMethod.POST)
	public ModelAndView exapmle(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ViewConstants.INDEX);
		return mav;
	}

}
