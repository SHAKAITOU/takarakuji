package sha.work.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.framework.service.BaseService;
import sha.framework.util.MultipleDBAccessUtil;
import sha.framework.util.PropertiesUtil;
import sha.work.data.S002OutputData;
import sha.work.exception.TKRKScreenException;
import sha.work.logic.LogicTest;
import sha.work.mapper.db1.Tran_statusMapper;
import sha.work.mapper.db1.UserMapper;
import sha.work.mapper.db2.User2Mapper;
import sha.work.mapper.domain.User;

@Service
public class MBTestService extends BaseService {


    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private User2Mapper user2Mapper;
    
    @Autowired
    private MultipleDBAccessUtil dbAccess;
    
    @Autowired
    private Tran_statusMapper tran_statusMapper;
    
    @Autowired
    private LogicTest logicTest;



	public String findByName(String name) {
		return userMapper.findByName(name).toString();
	}


	public void logic(InputBaseData input, OutputBaseData output) throws TKRKScreenException {
		// TODO Auto-generated method stub
		//User user = getByName("bob");
		//User user = userMapper.findByName("bob");
		//User user2 =  user2Mapper.findByName("jackson");
		UserMapper user = (UserMapper)dbAccess.getMapper(UserMapper.class);
		User user2 = user.getByName("bob");
		((S002OutputData)output).setUser(user2);
		PropertiesUtil propertiesUtil = new PropertiesUtil(new File("D:\\sya-kt\\resource\\old\\messages.properties"));
		((S002OutputData)output).setLocalProp(propertiesUtil.getProperty("MSG004"));
		List<String> arr = Arrays.asList(
	            StringEscapeUtils.escapeCsv("a\",b[\\b,'\""),
	            StringEscapeUtils.escapeEcmaScript("<script>alert('XSS');</script>"),
	            StringEscapeUtils.escapeJava("\"java's\"Hello!t#$%&'"),
	            StringEscapeUtils.escapeJson("{response:{text:'He didn't say, \"Stop!\"',body:{el:$('.responseXHR'),html:'<div class=\"message\"'>success</div>'}}}"),
	            StringEscapeUtils.escapeXml10("<script>alert('XSS');</script>"),
	            StringEscapeUtils.escapeXml11("<script>alert('XSS');</script>"),
	            StringEscapeUtils.escapeHtml4("<script>alert('XSS');</script>")
	        );
	        arr.forEach(str -> System.out.println(str));

		//tran_statusMapper.tran_status_Register(user.getName());
		//tran_statusMapper.sales_data_Register(user.getName());
		//tran_statusMapper.tran_data_Register(user.getName());
//		LogUtil.writeLog(LogUtil.DEBUG_LEVEL, "DEBUGテスト", 
//				DateCommonUtil.getFormatDateString(new Date(), DateCommonUtil.DATE_FORMAT_YYYYSMMSDD));
	}

}
