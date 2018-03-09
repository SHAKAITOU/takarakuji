package sha.work.controller.batch;

import java.io.File;
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
import sha.framework.util.JsonLogCommonUtil;
import sha.framework.util.MessageSourceUtil;
import sha.work.exception.TKRKScreenException;
import sha.work.service.loto.Loto6AnalysisP1Service;
import sha.work.util.FileUtil;

/**
 * S002 Thymeleaf 
 * @author 000001A009A6A
 *
 */
@Controller
@RequestMapping("/batch/loto6AnalysisP1CreateFile")
public class Loto6AnalysisP1CreateFileController extends ScreenBaseController{
	
	
	@Autowired
	private MessageSourceUtil msg;

	
	@Autowired
	private JsonLogCommonUtil jsonLog;
	
	@Autowired
	private Loto6AnalysisP1Service service;	


	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView exapmle(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {


		ModelAndView mav = new ModelAndView();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getLoto6P1DataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, service.analysis());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
		mav.setViewName("/common/success");
		return mav;
	}


}
