package sha.work.service.loto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.dto.loto.Loto7;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class Loto7AddService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7Mapper loto7Mapper;
	
	@Autowired
	private Loto7AnalysisBaseDataCreateService analysisBaseService;

	public Loto7 getLastTurn() throws TKRKScreenException {
		
		return loto7Mapper.findLastTurn();
	}
	
	@Transactional
	public void add(Loto7 loto7) {
		int cnt = loto7Mapper.isExist(loto7.getTurn());
		if(cnt > 0) {
			loto7Mapper.update(loto7);
		} else {
			loto7Mapper.save(loto7);
		}
		analysisBaseService.analysisAndSave(loto7.getTurn());
		
	}

}
