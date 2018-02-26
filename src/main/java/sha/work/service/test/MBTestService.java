package sha.work.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.framework.service.BaseService;
import sha.framework.util.MultipleDBAccessUtil;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class MBTestService extends BaseService {



	@Autowired
	private MultipleDBAccessUtil dbAccess;

	/** DB access class. */
	@Autowired
	private Loto7Mapper mapper;


	public void logic(InputBaseData input, OutputBaseData output) throws TKRKScreenException {
		// TODO Auto-generated method stub
		//User user = getByName("bob");
		//User user = userMapper.findByName("bob");
		//User user2 =  user2Mapper.findByName("jackson");
		//		UserMapper user = (UserMapper)dbAccess.getMapper(UserMapper.class);
		//		User user2 = user.getByName("bob");
		//		((S002OutputData)output).setUser(user2);
		//		PropertiesUtil propertiesUtil = new PropertiesUtil(new File("D:\\sya-kt\\resource\\old\\messages.properties"));
		//		((S002OutputData)output).setLocalProp(propertiesUtil.getProperty("MSG004"));
		//		List<String> arr = Arrays.asList(
		//	            StringEscapeUtils.escapeCsv("a\",b[\\b,'\""),
		//	            StringEscapeUtils.escapeEcmaScript("<script>alert('XSS');</script>"),
		//	            StringEscapeUtils.escapeJava("\"java's\"Hello!t#$%&'"),
		//	            StringEscapeUtils.escapeJson("{response:{text:'He didn't say, \"Stop!\"',body:{el:$('.responseXHR'),html:'<div class=\"message\"'>success</div>'}}}"),
		//	            StringEscapeUtils.escapeXml10("<script>alert('XSS');</script>"),
		//	            StringEscapeUtils.escapeXml11("<script>alert('XSS');</script>"),
		//	            StringEscapeUtils.escapeHtml4("<script>alert('XSS');</script>")
		//	        );
		//	        arr.forEach(str -> System.out.println(str));

		//importLoto7Data();
		//importLoto6Data(); 
		//importNumberS4Data();
		//importNumberS3Data();
		//importBinGo5Data();
		//importMiniLotoData();
		//importHanyoData();
		
		
		//tran_statusMapper.tran_status_Register(user.getName());
		//tran_statusMapper.sales_data_Register(user.getName());
		//tran_statusMapper.tran_data_Register(user.getName());
		//		LogUtil.writeLog(LogUtil.DEBUG_LEVEL, "DEBUGテスト", 
		//				DateCommonUtil.getFormatDateString(new Date(), DateCommonUtil.DATE_FORMAT_YYYYSMMSDD));
	}

}
