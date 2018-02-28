package sha.work.service.loto.init;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.framework.util.FileReaderUtil;
import sha.work.dto.loto.NumberS4;
import sha.work.dto.loto.def.NumberS4Table;
import sha.work.mapper.loto.NumberS4Mapper;
import sha.work.util.FileUtil;

@Service
public class NumberS4ImportDataService extends BaseService {

	/** DB access class. */
	@Autowired
	private NumberS4Mapper numberS4Mapper;
	
	@Autowired
    ResourceLoader resourceLoader;

	public void importData() {

		List<List<String>> csvData = FileReaderUtil.readCsv(new File(FileUtil.getNumberS4ImportDataFileCsv()));

		for(List<String> data : csvData) {
			NumberS4 numberS4 = new NumberS4();
			numberS4.setTurn(Integer.valueOf(data.get(NumberS4Table.TURN.getId())));
			numberS4.setOpenDt(data.get(NumberS4Table.OPEN_DT.getId()));
			numberS4.setL1(Integer.valueOf(data.get(NumberS4Table.L1.getId())));
			numberS4.setL2(Integer.valueOf(data.get(NumberS4Table.L2.getId())));
			numberS4.setL3(Integer.valueOf(data.get(NumberS4Table.L3.getId())));
			numberS4.setL4(Integer.valueOf(data.get(NumberS4Table.L4.getId())));

			numberS4Mapper.save(numberS4);
		}
	}
}
