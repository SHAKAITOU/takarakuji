package sha.work.controller.loto;

import java.io.IOException;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import sha.framework.controller.ScreenBaseController;
import sha.framework.util.FileReaderUtil;
import sha.framework.util.JsonLogCommonUtil;
import sha.framework.util.MessageSourceUtil;
import sha.work.entity.out.Loto7AnalysisP1Out;
import sha.work.entity.out.Loto7AnalysisP2Out;
import sha.work.exception.TKRKScreenException;
import sha.work.service.loto.Loto7AnalysisP2Service;
import sha.work.util.FileUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto7AnalysisP2Controller extends ScreenBaseController{
	
	
	@Autowired
	private MessageSourceUtil msg;

	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private Loto7AnalysisP2Service p2Service; 


	@RequestMapping(path="/loto/loto7AnalysisP2", method=RequestMethod.GET)
	public ModelAndView getLoto7AnalysisP2(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		
		Loto7AnalysisP2Out analysisP2Out = p2Service.summary();
		
		mav.addObject("resultList", analysisP2Out);
		mav.setViewName("loto/loto7AnalysisP2Result");
		
		return mav;
	}

}
