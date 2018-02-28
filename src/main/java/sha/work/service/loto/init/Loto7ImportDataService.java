package sha.work.service.loto.init;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.framework.util.FileReaderUtil;
import sha.work.dto.loto.Loto7;
import sha.work.dto.loto.def.Loto7Table;
import sha.work.mapper.loto.Loto7Mapper;
import sha.work.util.FileUtil;

@Service
public class Loto7ImportDataService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7Mapper loto7Mapper;
	
	@Autowired
    ResourceLoader resourceLoader;

	public void importData() {

		List<List<String>> csvData = FileReaderUtil.readCsv(new File(FileUtil.getLoto7ImportDataFileCsv()));

		for(List<String> data : csvData) {
			Loto7 loto7 = new Loto7();
			loto7.setTurn(Integer.valueOf(data.get(Loto7Table.TURN.getId())));
			loto7.setOpenDt(data.get(Loto7Table.OPEN_DT.getId()));
			loto7.setL1(Integer.valueOf(data.get(Loto7Table.L1.getId())));
			loto7.setL2(Integer.valueOf(data.get(Loto7Table.L2.getId())));
			loto7.setL3(Integer.valueOf(data.get(Loto7Table.L3.getId())));
			loto7.setL4(Integer.valueOf(data.get(Loto7Table.L4.getId())));
			loto7.setL5(Integer.valueOf(data.get(Loto7Table.L5.getId())));
			loto7.setL6(Integer.valueOf(data.get(Loto7Table.L6.getId())));
			loto7.setL7(Integer.valueOf(data.get(Loto7Table.L7.getId())));
			loto7.setB1(Integer.valueOf(data.get(Loto7Table.B1.getId())));
			loto7.setB2(Integer.valueOf(data.get(Loto7Table.B2.getId())));

			loto7Mapper.save(loto7);
		}
	}
}
