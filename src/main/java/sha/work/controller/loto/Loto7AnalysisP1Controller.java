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
import sha.work.exception.TKRKScreenException;
import sha.work.util.FileUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto7AnalysisP1Controller extends ScreenBaseController{
	
	
	@Autowired
	private MessageSourceUtil msg;

	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private ObjectMapper objMapper; 


	@RequestMapping(path="/loto/loto7AnalysisP1", method=RequestMethod.GET)
	public ModelAndView getLoto7AnalysisP1(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList", getAnaLysisData());
		mav.setViewName("loto/loto7AnalysisP1Result");
		
		return mav;
	}
	
	@RequestMapping(path="/loto/loto7AnalysisP1Group", method=RequestMethod.GET)
	public ModelAndView getLoto7AnalysisP1Group(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList", getAnaLysisData());
		mav.setViewName("loto/loto7AnalysisP1ResultGroup");
		
		return mav;
	}

	private Loto7AnalysisP1Out getAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto7P1DataFileJson());
			return objMapper.readValue(data, Loto7AnalysisP1Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	

}
