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
import sha.work.entity.out.Loto7AnalysisP2Out;
import sha.work.exception.TKRKScreenException;
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
	private ObjectMapper objMapper;  


	@RequestMapping(path=UrlConstants.LOTO_LOTO7ANALYSISP2, method=RequestMethod.GET)
	public ModelAndView getLoto7AnalysisP2(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		
		mav.addObject("resultList", getAnaLysisData());
		mav.setViewName(ViewConstants.LOTO_LOTO7ANALYSISP2RESULT);
		
		return mav;
	}
	
	private Loto7AnalysisP2Out getAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto7P2DataFileJson());
			return objMapper.readValue(data, Loto7AnalysisP2Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}

}
