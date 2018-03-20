package sha.work.service.loto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.dto.loto.Loto6;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto6Mapper;

@Service
public class Loto6AddService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto6Mapper loto6Mapper;
	
	@Autowired
	private Loto6AnalysisBaseDataCreateService analysisBaseService;

	public Loto6 getLastTurn() throws TKRKScreenException {
		
		return loto6Mapper.findLastTurn();
	}
	
	@Transactional
	public void add(Loto6 loto6) {
		int cnt = loto6Mapper.isExist(loto6.getTurn());
		if(cnt > 0) {
			loto6Mapper.update(loto6);
		} else {
			loto6Mapper.save(loto6);
		}
		analysisBaseService.analysisAndSave(loto6.getTurn());
		
	}

}
