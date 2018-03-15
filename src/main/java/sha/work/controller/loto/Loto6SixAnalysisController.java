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
import sha.work.entity.out.Loto6AnalysisP1Out;
import sha.work.entity.out.Loto6SixAnalysisOut;
import sha.work.exception.TKRKScreenException;
import sha.work.util.FileUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class Loto6SixAnalysisController extends ScreenBaseController{
	
	
	@Autowired
	private MessageSourceUtil msg;

	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private ObjectMapper objMapper;  


	@RequestMapping(path="/loto/loto6SixAnalysis", method=RequestMethod.GET)
	public ModelAndView getLoto7SevenAnalysis(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		
		mav.addObject("resultList", getSixAnaLysisData());
		mav.addObject("resultBaseList", getBaseAnaLysisData());
		mav.setViewName("loto/loto6SixAnalysisResult");
		
		return mav;
	}
	
	private Loto6SixAnalysisOut getSixAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto6SixDataFileJson());
			return objMapper.readValue(data, Loto6SixAnalysisOut.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private Loto6AnalysisP1Out getBaseAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getLoto6P1DataFileJson());
			return objMapper.readValue(data, Loto6AnalysisP1Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}

}
