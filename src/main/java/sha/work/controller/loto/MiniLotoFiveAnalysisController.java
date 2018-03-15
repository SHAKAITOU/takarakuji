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
import sha.work.entity.out.MiniLotoAnalysisP1Out;
import sha.work.entity.out.MiniLotoFiveAnalysisOut;
import sha.work.exception.TKRKScreenException;
import sha.work.util.FileUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
public class MiniLotoFiveAnalysisController extends ScreenBaseController{
	
	
	@Autowired
	private MessageSourceUtil msg;

	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private ObjectMapper objMapper;  


	@RequestMapping(path="/loto/miniLotoFiveAnalysis", method=RequestMethod.GET)
	public ModelAndView getLoto7SevenAnalysis(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		
		mav.addObject("resultList", getFiveAnaLysisData());
		mav.addObject("resultBaseList", getBaseAnaLysisData());
		mav.setViewName("loto/miniLotoFiveAnalysisResult");
		
		return mav;
	}
	
	private MiniLotoFiveAnalysisOut getFiveAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getMiniLotoFiveDataFileJson());
			return objMapper.readValue(data, MiniLotoFiveAnalysisOut.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}
	
	private MiniLotoAnalysisP1Out getBaseAnaLysisData() {
		try {
			String data = FileReaderUtil.read(FileUtil.getMiniLotoP1DataFileJson());
			return objMapper.readValue(data, MiniLotoAnalysisP1Out.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TKRKScreenException(e);
		}
	}

}
