package sha.work.service.loto;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.framework.util.CsvFileReader;
import sha.work.dto.loto.NumberS3;
import sha.work.dto.loto.def.NumberS3Table;
import sha.work.exception.TKRKScreenException;
import sha.work.loto.LotoConstant;
import sha.work.mapper.loto.NumberS3Mapper;

@Service
public class NumberS3ImportDataService extends BaseService {

	/** DB access class. */
	@Autowired
	private NumberS3Mapper numberS3Mapper;
	
	@Autowired
    ResourceLoader resourceLoader;

	public void importData() {
		Resource resource = resourceLoader.getResource("classpath:" + LotoConstant.LOTO6+LotoConstant.CSV);

		List<List<String>> csvData;
		try {
			csvData = CsvFileReader.read(resource.getFile());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}

		for(List<String> data : csvData) {
			NumberS3 numberS3 = new NumberS3();
			numberS3.setTurn(Integer.valueOf(data.get(NumberS3Table.TURN.getId())));
			numberS3.setOpenDt(data.get(NumberS3Table.OPEN_DT.getId()));
			numberS3.setL1(Integer.valueOf(data.get(NumberS3Table.L1.getId())));
			numberS3.setL2(Integer.valueOf(data.get(NumberS3Table.L2.getId())));
			numberS3.setL3(Integer.valueOf(data.get(NumberS3Table.L3.getId())));

			numberS3Mapper.save(numberS3);
		}
	}
}
