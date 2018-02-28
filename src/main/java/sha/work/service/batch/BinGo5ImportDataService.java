package sha.work.service.batch;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.framework.util.FileReaderUtil;
import sha.work.dto.loto.BinGo5;
import sha.work.dto.loto.def.BinGo5Table;
import sha.work.mapper.loto.BinGo5Mapper;
import sha.work.util.FileUtil;

@Service
public class BinGo5ImportDataService extends BaseService {

	/** DB access class. */
	@Autowired
	private BinGo5Mapper binGo5Mapper;

	public void importData() {

		List<List<String>> csvData = FileReaderUtil.readCsv(new File(FileUtil.getBinGo5ImportDataFileCsv()));

		for(List<String> data : csvData) {
			BinGo5 binGo5 = new BinGo5();
			binGo5.setTurn(Integer.valueOf(data.get(BinGo5Table.TURN.getId())));
			binGo5.setOpenDt(data.get(BinGo5Table.OPEN_DT.getId()));
			binGo5.setL1(Integer.valueOf(data.get(BinGo5Table.L1.getId())));
			binGo5.setL2(Integer.valueOf(data.get(BinGo5Table.L2.getId())));
			binGo5.setL3(Integer.valueOf(data.get(BinGo5Table.L3.getId())));
			binGo5.setL4(Integer.valueOf(data.get(BinGo5Table.L4.getId())));
			binGo5.setL5(Integer.valueOf(data.get(BinGo5Table.L5.getId())));
			binGo5.setL6(Integer.valueOf(data.get(BinGo5Table.L6.getId())));
			binGo5.setL7(Integer.valueOf(data.get(BinGo5Table.L7.getId())));
			binGo5.setL8(Integer.valueOf(data.get(BinGo5Table.L8.getId())));

			binGo5Mapper.save(binGo5);
		}
	}
}
