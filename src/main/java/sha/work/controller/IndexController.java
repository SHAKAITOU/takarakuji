package sha.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sha.framework.controller.ScreenBaseController;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping("/")
public class IndexController extends ScreenBaseController{

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView exapmle(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index");
		return mav;
	}

}
