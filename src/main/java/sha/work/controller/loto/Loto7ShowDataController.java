package sha.work.controller.loto;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import sha.framework.controller.ScreenBaseController;
import sha.framework.exception.TKRKScreenException;
import sha.framework.util.DateUtility;
import sha.framework.util.DateUtility.DateFormat;
import sha.framework.util.LogCommonUtil;
import sha.work.entity.in.Loto7ShowDataIn;
import sha.work.entity.out.Loto7ShowDataOut;
import sha.work.enums.PageCntType;
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
	public ModelAndView showLoto7DataGet(@ModelAttribute Object greeting)  {
		
		ModelAndView mav = new ModelAndView();
		
		
		Loto7ShowDataIn dataIn = initLoto7ShowDataIn();
		Loto7ShowDataOut dataOut = new Loto7ShowDataOut();
		
		try {
			BeanUtils.copyProperties(dataOut, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}
		dataOut.setLoto7List(service.getData(dataIn));
		
		mav.addObject("result", dataOut);
		mav.setViewName("loto/loto7DataShow");
		return mav;
	}
	
	@RequestMapping(path="/loto/showLoto7Data", method=RequestMethod.POST)
	public ModelAndView showLoto7DataPost(@RequestParam Map<String,String> allRequestParams, Locale loc, 
			HttpServletRequest request,
			HttpServletResponse response) throws TKRKScreenException, JsonProcessingException   {
		
		ModelAndView mav = new ModelAndView();
		Loto7ShowDataIn dataIn = searchLoto7ShowDataIn(allRequestParams);

		Loto7ShowDataOut dataOut = new Loto7ShowDataOut();
		
		try {
			BeanUtils.copyProperties(dataOut, dataIn);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TKRKScreenException(e);
		}

		dataOut.setLoto7List(service.getData(dataIn));
		
		mav.addObject("result", dataOut);
		mav.setViewName("loto/loto7DataShow");
		return mav;
	}
	
	private Loto7ShowDataIn initLoto7ShowDataIn() {
		Loto7ShowDataIn dataIn = new Loto7ShowDataIn();
		dataIn.setPageCntRadio(PageCntType.MAX_10.getId());
		dataIn.setSearchTypeRadio(SearchType.BY_TURN.getKey());
		dataIn.setSearchDt(DateUtility.formatDate(LocalDate.now(), DateFormat.UUUUSMMSDD.getFormat()));
		int maxTurn = service.getMaxTurn();
		dataIn.setSearchTurnFrom(maxTurn-dataIn.getPageCntRadio());
		dataIn.setMaxTurn(maxTurn);
		
		return dataIn;
	}
	
	private Loto7ShowDataIn searchLoto7ShowDataIn(Map<String,String> allRequestParams) {
		Loto7ShowDataIn dataIn = new Loto7ShowDataIn();
		dataIn.setPageCntRadio(Integer.valueOf(allRequestParams.get("pageCntRadio")));
		dataIn.setSearchTypeRadio(allRequestParams.get("searchTypeRadio"));
		dataIn.setSearchDt(allRequestParams.get("searchDt"));
		if(!StringUtils.isEmpty(allRequestParams.get("searchTurnFrom"))) {
			dataIn.setSearchTurnFrom(Integer.valueOf(allRequestParams.get("searchTurnFrom")));
		}
		int maxTurn = service.getMaxTurn();
		dataIn.setMaxTurn(maxTurn);
		
		return dataIn;
	}

	public enum SearchType {

		BY_DATE(1, "0", "抽せん日から指定"),
		BY_TURN(2, "1", "回号から指定");
		
	    /** type. */
	    private int id;
	    private String key;
	    private String name;

	    private SearchType(int id, String key, String name) {
	        this.id = id;
	        this.key = key;
	        this.name = name;
	    }
	    
	    public int getId() {
	        return id;
	    }
	    
	    public String getKey() {
	        return key;
	    }
	    
	    public String getName() {
	        return name;
	    }

	    public SearchType valueOf(int id) {
	    	for(SearchType type : SearchType.values()) {
	    		if(id == type.getId()) {
	    			return type;
	    		}
	    	}
	    	
	    	return null;
	    }
	    
	    public SearchType keyOf(String key) {
	    	for(SearchType type : SearchType.values()) {
	    		if(key.equals(type.getKey())) {
	    			return type;
	    		}
	    	}
	    	
	    	return null;
	    }
	    
	    public SearchType nameOf(String name) {
	    	for(SearchType type : SearchType.values()) {
	    		if(name.equals(type.getName())) {
	    			return type;
	    		}
	    	}
	    	
	    	return null;
	    }
	} 

}
