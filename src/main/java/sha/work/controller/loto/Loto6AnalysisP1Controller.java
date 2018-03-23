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
import sha.work.common.UrlConstants;
import sha.work.common.ViewConstants;
import sha.work.entity.out.Loto6AnalysisP1Out;
import sha.work.exception.TKRKScreenException;
import sha.work.util.FileUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto6AnalysisP1Controller extends ScreenBaseController{
	
	
	@Autowired
	private MessageSourceUtil msg;

	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private ObjectMapper objMapper; 


	@RequestMapping(path=UrlConstants.LOTO_LOTO6ANALYSISP1, method=RequestMethod.GET)
	public ModelAndView getLoto6AnalysisP1(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList", getAnaLysisData());
		mav.setViewName(ViewConstants.LOTO_LOTO6ANALYSISP1RESULT);
		
		return mav;
	}
	
	@RequestMapping(path=UrlConstants.LOTO_LOTO6ANALYSISP1GROUP, method=RequestMethod.GET)
	public ModelAndView getLoto6AnalysisP1Group(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList", getAnaLysisData());
		mav.setViewName(ViewConstants.LOTO_LOTO6ANALYSISP1RESULTGROUP);
		
		return mav;
	}

	private Loto6AnalysisP1Out getAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto6P1DataFileJson());
			return objMapper.readValue(data, Loto6AnalysisP1Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	

}
