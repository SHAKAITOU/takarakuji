package sha.work.service.loto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.dto.loto.MiniLoto;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.MiniLotoMapper;

@Service
public class MiniLotoAddOrEditService extends BaseService {

	/** DB access class. */
	@Autowired
	private MiniLotoMapper miniLotoMapper;
	
	@Autowired
	private MiniLotoAnalysisBaseDataCreateService analysisBaseService;

	public MiniLoto getByTurn(int turn) throws TKRKScreenException {
		
		return miniLotoMapper.findByTurn(turn);
	}
	
	public MiniLoto getLastTurn() throws TKRKScreenException {
		
		return miniLotoMapper.findLastTurn();
	}
	
	@Transactional
	public void addOrEdit(MiniLoto miniLoto) {
		int cnt = miniLotoMapper.isExist(miniLoto.getTurn());
		if(cnt > 0) {
			miniLotoMapper.update(miniLoto);
		} else {
			miniLotoMapper.save(miniLoto);
		}
		analysisBaseService.analysisAndSave(miniLoto.getTurn());
		
	}
	

	@Transactional
	public boolean edit(MiniLoto miniLoto) {
		MiniLoto miniLotoDb = miniLotoMapper.findByTurn(miniLoto.getTurn());
		if(miniLotoDb != null) {
			miniLoto.setOpenDt(miniLotoDb.getOpenDt());
			miniLotoMapper.update(miniLoto);
			analysisBaseService.analysisAndSave(miniLoto.getTurn());
			return true;
		}
		
		return false;
	}

}
