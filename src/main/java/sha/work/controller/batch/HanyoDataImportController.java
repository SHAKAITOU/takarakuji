package sha.work.controller.batch;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sha.framework.controller.ScreenBaseController;
import sha.framework.util.LogCommonUtil;
import sha.work.service.batch.HanyoDataImportService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping("/batch/importHanyoData")
public class HanyoDataImportController extends ScreenBaseController{

	


	@Autowired
	private LogCommonUtil log;

	@Autowired
	private HanyoDataImportService service;

	@Autowired
	private Validator validator;


	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView exapmle(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		service.importHanyoData();

		mav.setViewName("/common/success");
		return mav;
	}


}
