package sha.work.service.loto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.dto.loto.MiniLoto;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.MiniLotoMapper;

@Service
public class MiniLotoAddService extends BaseService {

	/** DB access class. */
	@Autowired
	private MiniLotoMapper miniLotoMapper;
	
	@Autowired
	private MiniLotoAnalysisBaseDataCreateService analysisBaseService;

	public MiniLoto getLastTurn() throws TKRKScreenException {
		
		return miniLotoMapper.findLastTurn();
	}
	
	@Transactional
	public void add(MiniLoto miniLoto) {
		int cnt = miniLotoMapper.isExist(miniLoto.getTurn());
		if(cnt > 0) {
			miniLotoMapper.update(miniLoto);
		} else {
			miniLotoMapper.save(miniLoto);
		}
		analysisBaseService.analysisAndSave(miniLoto.getTurn());
		
	}

}
