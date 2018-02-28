package sha.work.controller.loto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sha.framework.controller.ScreenBaseController;
import sha.framework.util.LogCommonUtil;
import sha.work.service.loto.Loto7ShowService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto7ShowDataController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private Loto7ShowService service;


	@RequestMapping(path="/loto/showLoto7Data", method=RequestMethod.GET)
	public ModelAndView exapmle(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList", service.getData(0));
		mav.setViewName("loto/loto7DataShow");
		return mav;
	}


}
