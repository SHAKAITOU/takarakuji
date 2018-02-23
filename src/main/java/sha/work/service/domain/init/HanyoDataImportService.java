package sha.work.service.domain.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.framework.util.MultipleDBAccessUtil;
import sha.work.dto.domain.Hanyo;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.domain.HanyoMapper;

@Service
public class HanyoDataImportService extends BaseService {



	@Autowired
	private MultipleDBAccessUtil dbAccess;

	@Transactional
	public void importHanyoData() throws TKRKScreenException {
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
