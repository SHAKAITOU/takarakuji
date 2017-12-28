package sha.work.controller;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sha.framework.controller.ScreenBaseController;
import sha.framework.util.LogCommonUtil;
import sha.work.data.S002InputData;
import sha.work.data.S002OutputData;
import sha.work.service.MBTestService;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping("/S002R")
public class S002ResultController extends ScreenBaseController{

	@Autowired
	private LogCommonUtil log;

	@Autowired
	private MBTestService mBTestService;

	@Autowired
	private Validator validator;


	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView exapmle(@ModelAttribute S002InputData greeting)  {
	  
	  
	  Set<ConstraintViolation<S002InputData>> validateResult =  validator.validate(greeting);
	  
	  for (final ConstraintViolation<S002InputData> result : validateResult) {
        
	    log.error("VALIDATION ERROR:paramater name", result.getPropertyPath().toString());
	    log.error("VALIDATION ERROR:paramater value", result.getInvalidValue().toString());
	    log.error("VALIDATION ERROR:error message", result.getMessage());
    }


		S002OutputData output = new S002OutputData();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("greeting", greeting);
		
		log.info("CONTROLLERテスト", "this is a info msg.");
		log.info("CONTROLLERテスト", "this is a info msg.", greeting);
		
		log.notic("CONTROLLERテスト", "this is a notic msg.");
		log.notic("CONTROLLERテスト", "this is a notic msg.", greeting);
		
		log.warn("CONTROLLERテスト", "this is a warn msg.");
		log.warn("CONTROLLERテスト", "this is a warn msg.", greeting);
		
		log.error("CONTROLLERテスト", "this is a error msg.");
		log.error("CONTROLLERテスト", "this is a error msg.", greeting);
		
		log.critical("CONTROLLERテスト", "this is a critical msg.");
		log.critical("CONTROLLERテスト", "this is a critical msg.", greeting);

		mBTestService.logic(greeting, output);
		
		//salesResistrationService.receiveMessage("1S002R");
		
		mav.addObject("output", output);
		mav.setViewName("S002R");
		log.info("CONTROLLERテスト", "this is a info msg.", mav);
		return mav;
	}




}
