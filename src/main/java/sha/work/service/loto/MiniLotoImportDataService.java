package sha.work.service.loto;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.framework.util.CsvFileReader;
import sha.work.dto.loto.MiniLoto;
import sha.work.dto.loto.def.MiniLotoTable;
import sha.work.exception.TKRKScreenException;
import sha.work.loto.LotoConstant;
import sha.work.mapper.loto.MiniLotoMapper;

@Service
public class MiniLotoImportDataService extends BaseService {

	/** DB access class. */
	@Autowired
	private MiniLotoMapper miniLotoMapper;
	
	@Autowired
    ResourceLoader resourceLoader;

	public void importData() {
		Resource resource = resourceLoader.getResource("classpath:" + LotoConstant.MINILOTO+LotoConstant.CSV);

		List<List<String>> csvData;
		try {
			csvData = CsvFileReader.read(resource.getFile());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}

		for(List<String> data : csvData) {
			MiniLoto miniLoto = new MiniLoto();
			miniLoto.setTurn(Integer.valueOf(data.get(MiniLotoTable.TURN.getId())));
			miniLoto.setOpenDt(data.get(MiniLotoTable.OPEN_DT.getId()));
			miniLoto.setL1(Integer.valueOf(data.get(MiniLotoTable.L1.getId())));
			miniLoto.setL2(Integer.valueOf(data.get(MiniLotoTable.L2.getId())));
			miniLoto.setL3(Integer.valueOf(data.get(MiniLotoTable.L3.getId())));
			miniLoto.setL4(Integer.valueOf(data.get(MiniLotoTable.L4.getId())));
			miniLoto.setL5(Integer.valueOf(data.get(MiniLotoTable.L5.getId())));
			miniLoto.setB1(Integer.valueOf(data.get(MiniLotoTable.B1.getId())));

			miniLotoMapper.save(miniLoto);
		}
	}
}
