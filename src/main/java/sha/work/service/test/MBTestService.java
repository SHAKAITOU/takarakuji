package sha.work.service.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.framework.service.BaseService;
import sha.framework.util.CsvFileReader;
import sha.framework.util.MultipleDBAccessUtil;
import sha.work.dto.domain.Hanyo;
import sha.work.dto.loto.BinGo5;
import sha.work.dto.loto.Loto6;
import sha.work.dto.loto.Loto7;
import sha.work.dto.loto.MiniLoto;
import sha.work.dto.loto.NumberS3;
import sha.work.dto.loto.NumberS4;
import sha.work.exception.TKRKScreenException;
import sha.work.loto.LotoConstant;
import sha.work.mapper.domain.HanyoMapper;
import sha.work.mapper.loto.BinGo5Mapper;
import sha.work.mapper.loto.Loto6Mapper;
import sha.work.mapper.loto.Loto7Mapper;
import sha.work.mapper.loto.MiniLotoMapper;
import sha.work.mapper.loto.NumberS3Mapper;
import sha.work.mapper.loto.NumberS4Mapper;

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

	private void importLoto7Data() {
		Loto7Mapper mapper = (Loto7Mapper)dbAccess.getMapper(Loto7Mapper.class);
		List<List<String>> csvData = CsvFileReader.read(LotoConstant.LOTO7+LotoConstant.CSV);

		for(List<String> data : csvData) {
			Loto7 loto7 = new Loto7();
			loto7.setTurn(Integer.valueOf(data.get(Loto7Mapper.IDX_TURN)));
			loto7.setOpenDt(data.get(Loto7Mapper.IDX_OPEN_DT));
			loto7.setL1(Integer.valueOf(data.get(Loto7Mapper.IDX_L1)));
			loto7.setL2(Integer.valueOf(data.get(Loto7Mapper.IDX_L2)));
			loto7.setL3(Integer.valueOf(data.get(Loto7Mapper.IDX_L3)));
			loto7.setL4(Integer.valueOf(data.get(Loto7Mapper.IDX_L4)));
			loto7.setL5(Integer.valueOf(data.get(Loto7Mapper.IDX_L5)));
			loto7.setL6(Integer.valueOf(data.get(Loto7Mapper.IDX_L6)));
			loto7.setL7(Integer.valueOf(data.get(Loto7Mapper.IDX_L7)));
			loto7.setB1(Integer.valueOf(data.get(Loto7Mapper.IDX_B1)));
			loto7.setB2(Integer.valueOf(data.get(Loto7Mapper.IDX_B2)));

			mapper.save(loto7);
		}
	}

	private void importLoto6Data() {
		Loto6Mapper mapper = (Loto6Mapper)dbAccess.getMapper(Loto6Mapper.class);
		List<List<String>> csvData = CsvFileReader.read(LotoConstant.LOTO6+LotoConstant.CSV);

		for(List<String> data : csvData) {
			Loto6 loto6 = new Loto6();
			loto6.setTurn(Integer.valueOf(data.get(Loto6Mapper.IDX_TURN)));
			loto6.setOpenDt(data.get(Loto6Mapper.IDX_OPEN_DT));
			loto6.setL1(Integer.valueOf(data.get(Loto6Mapper.IDX_L1)));
			loto6.setL2(Integer.valueOf(data.get(Loto6Mapper.IDX_L2)));
			loto6.setL3(Integer.valueOf(data.get(Loto6Mapper.IDX_L3)));
			loto6.setL4(Integer.valueOf(data.get(Loto6Mapper.IDX_L4)));
			loto6.setL5(Integer.valueOf(data.get(Loto6Mapper.IDX_L5)));
			loto6.setL6(Integer.valueOf(data.get(Loto6Mapper.IDX_L6)));
			loto6.setB1(Integer.valueOf(data.get(Loto6Mapper.IDX_B1)));

			mapper.save(loto6);
		}
	}

	private void importNumberS4Data() {
		NumberS4Mapper mapper = (NumberS4Mapper)dbAccess.getMapper(NumberS4Mapper.class);
		List<List<String>> csvData = CsvFileReader.read(LotoConstant.NUMBERS4+LotoConstant.CSV);

		for(List<String> data : csvData) {
			NumberS4 numberS4 = new NumberS4();
			numberS4.setTurn(Integer.valueOf(data.get(NumberS4Mapper.IDX_TURN)));
			numberS4.setOpenDt(data.get(NumberS4Mapper.IDX_OPEN_DT));
			numberS4.setL1(Integer.valueOf(data.get(NumberS4Mapper.IDX_L1)));
			numberS4.setL2(Integer.valueOf(data.get(NumberS4Mapper.IDX_L2)));
			numberS4.setL3(Integer.valueOf(data.get(NumberS4Mapper.IDX_L3)));
			numberS4.setL4(Integer.valueOf(data.get(NumberS4Mapper.IDX_L4)));

			mapper.save(numberS4);
		}
	}
	
	private void importNumberS3Data() {
		NumberS3Mapper mapper = (NumberS3Mapper)dbAccess.getMapper(NumberS3Mapper.class);
		List<List<String>> csvData = CsvFileReader.read(LotoConstant.NUMBERS3+LotoConstant.CSV);

		for(List<String> data : csvData) {
			NumberS3 numberS3 = new NumberS3();
			numberS3.setTurn(Integer.valueOf(data.get(NumberS3Mapper.IDX_TURN)));
			numberS3.setOpenDt(data.get(NumberS3Mapper.IDX_OPEN_DT));
			numberS3.setL1(Integer.valueOf(data.get(NumberS3Mapper.IDX_L1)));
			numberS3.setL2(Integer.valueOf(data.get(NumberS3Mapper.IDX_L2)));
			numberS3.setL3(Integer.valueOf(data.get(NumberS3Mapper.IDX_L3)));

			mapper.save(numberS3);
		}
	}
	
	private void importBinGo5Data() {
		BinGo5Mapper mapper = (BinGo5Mapper)dbAccess.getMapper(BinGo5Mapper.class);
		List<List<String>> csvData = CsvFileReader.read(LotoConstant.BINGO5+LotoConstant.CSV);

		for(List<String> data : csvData) {
			BinGo5 binGo5 = new BinGo5();
			binGo5.setTurn(Integer.valueOf(data.get(BinGo5Mapper.IDX_TURN)));
			binGo5.setOpenDt(data.get(BinGo5Mapper.IDX_OPEN_DT));
			binGo5.setL1(Integer.valueOf(data.get(BinGo5Mapper.IDX_L1)));
			binGo5.setL2(Integer.valueOf(data.get(BinGo5Mapper.IDX_L2)));
			binGo5.setL3(Integer.valueOf(data.get(BinGo5Mapper.IDX_L3)));
			binGo5.setL4(Integer.valueOf(data.get(BinGo5Mapper.IDX_L4)));
			binGo5.setL5(Integer.valueOf(data.get(BinGo5Mapper.IDX_L5)));
			binGo5.setL6(Integer.valueOf(data.get(BinGo5Mapper.IDX_L6)));
			binGo5.setL7(Integer.valueOf(data.get(BinGo5Mapper.IDX_L7)));
			binGo5.setL8(Integer.valueOf(data.get(BinGo5Mapper.IDX_L8)));

			mapper.save(binGo5);
		}
	}
	
	private void importMiniLotoData() {
		MiniLotoMapper mapper = (MiniLotoMapper)dbAccess.getMapper(MiniLotoMapper.class);
		List<List<String>> csvData = CsvFileReader.read(LotoConstant.MINILOTO+LotoConstant.CSV);

		for(List<String> data : csvData) {
			MiniLoto miniLoto = new MiniLoto();
			miniLoto.setTurn(Integer.valueOf(data.get(MiniLotoMapper.IDX_TURN)));
			miniLoto.setOpenDt(data.get(MiniLotoMapper.IDX_OPEN_DT));
			miniLoto.setL1(Integer.valueOf(data.get(MiniLotoMapper.IDX_L1)));
			miniLoto.setL2(Integer.valueOf(data.get(MiniLotoMapper.IDX_L2)));
			miniLoto.setL3(Integer.valueOf(data.get(MiniLotoMapper.IDX_L3)));
			miniLoto.setL4(Integer.valueOf(data.get(MiniLotoMapper.IDX_L4)));
			miniLoto.setL5(Integer.valueOf(data.get(MiniLotoMapper.IDX_L5)));
			miniLoto.setB1(Integer.valueOf(data.get(MiniLotoMapper.IDX_B1)));

			mapper.save(miniLoto);
		}
	}
	
	private void importHanyoData() {
		HanyoMapper mapper = (HanyoMapper)dbAccess.getMapper(HanyoMapper.class);
		//loto7
		for(int i=1; i<=37; i++) {
			Hanyo hanyo = new Hanyo();
			hanyo.setId(1);
			hanyo.setCode(String.format("%04d", i));
			hanyo.setName("LOTO7_NO_"+i);
			hanyo.setValue(i+"");
			mapper.save(hanyo);
		}
		
		//loto6
		for(int i=1; i<=43; i++) {
			Hanyo hanyo = new Hanyo();
			hanyo.setId(2);
			hanyo.setCode(String.format("%04d", i));
			hanyo.setName("LOTO6_NO_"+i);
			hanyo.setValue(i+"");
			mapper.save(hanyo);
		}
		
		//mini loto
		for(int i=1; i<=31; i++) {
			Hanyo hanyo = new Hanyo();
			hanyo.setId(3);
			hanyo.setCode(String.format("%04d", i));
			hanyo.setName("MINI_LOTO_NO_"+i);
			hanyo.setValue(i+"");
			mapper.save(hanyo);
		}
		
		//numberS3, numberS4
		for(int i=0; i<=9; i++) {
			Hanyo hanyo = new Hanyo();
			hanyo.setId(4);
			hanyo.setCode(String.format("%04d", i));
			hanyo.setName("NUMBER_S3_NO_"+i);
			hanyo.setValue(i+"");
			mapper.save(hanyo);
			
			hanyo = new Hanyo();
			hanyo.setId(5);
			hanyo.setCode(String.format("%04d", i));
			hanyo.setName("NUMBER_S4_NO_"+i);
			hanyo.setValue(i+"");
			mapper.save(hanyo);
		}
		
		//bingo5
		for(int i=1; i<=40; i++) {
			Hanyo hanyo = new Hanyo();
			hanyo.setId(6);
			hanyo.setCode(String.format("%04d", i));
			hanyo.setName("BINGO5_NO_"+i);
			hanyo.setValue(i+"");
			mapper.save(hanyo);
		}
	}

}
